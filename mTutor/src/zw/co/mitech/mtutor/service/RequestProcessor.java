package zw.co.mitech.mtutor.service;

import java.io.Serializable;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zw.co.mitech.mtutor.entities.Assignment;
import zw.co.mitech.mtutor.entities.Quiz;
import zw.co.mitech.mtutor.entities.Student;
import zw.co.mitech.mtutor.entities.Subject;
import zw.co.mitech.mtutor.session.AssignmentFacade;
import zw.co.mitech.mtutor.session.QuizFacade;
import zw.co.mitech.mtutor.session.RankingsFacade;
import zw.co.mitech.mtutor.session.StudentFacade;
import zw.co.mitech.mtutor.session.StudentSubjectFacade;
import zw.co.mitech.mtutor.session.SubjectFacade;
import zw.co.mitech.mtutor.util.ApplicationConstants;
import zw.co.mitech.mtutor.util.Keywords;
import zw.co.mitech.mtutor.util.MenuLevel;
import zw.co.mitech.mtutor.util.Messages;
import zw.co.mitech.mtutor.util.OptionsUtil;
import zw.co.mitech.mtutor.util.Rank;
import zw.co.mitech.mtutor.util.StringUtil;
import zw.co.mitech.mtutor.util.Txt;

@Service("requestProcessor")
public class RequestProcessor implements Processor, Serializable {

	private static final long serialVersionUID = 4376057591528770840L;
	@Autowired
	private QuizQuestionService questionService;
	@Autowired
	private QuizService quizService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private RankingService rankingService;
	@Autowired
	private StudentFacade studentFacade;
	@Autowired
	private QuizFacade quizFacade;
	@Autowired
	private StudentProgressService progressService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private SubjectFacade subjectFacade;
	@Autowired
	private AssignmentFacade assignmentFacade;
	
	

	public static final String QUESTION_HOME_TEMPLATE_KEY = "question.home.template";
	public static final String ANSWER_HOME_TEMPLATE_KEY = "answer.home.template";
	public static final String ANSWER_HOME = "ANSWER_HOME";
	public static final String PREVIOUS_HOME = "PREVIOUS_HOME";
	public static final String PREVIOUS_MAX = "PREVIOUS_MAX";
	public static final String RESULTS_MAX = "RESULTS_MAX";
	public static final String ANSWER_PREFIX = "ANSWER_";
	public static final String QUESTION_PREFIX = "QUESTION_";

	public Txt processTxtRequest(Txt txt) {

		String keyword = txt.getToken(0);
		

		if (keyword == null) {
			keyword = "";
		}

		if (StringUtil.isEmpty(txt.getCustomerState())) {
			System.out.println("**** new student ");
			// register customer
			txt.setCustomerState(txt.getId());
			List<Student> students = studentFacade.findStudentByMobileNumber(txt.getId());
			if(students == null || students.isEmpty()){
				System.out.println("**** no students ");
				
				return studentService.registerStudent(txt);
			}else{
				System.out.println("**** students found ");
				
				txt.setMobileNumber(StringUtil.formatMobile(txt.getId()));
				return showHomePage(txt);
			}

		} else if (StringUtil.isEmpty(txt.getTxtMsg()) || StringUtil.isEmpty(keyword) && !StringUtil.isEmpty(txt.getCustomerState())) {
			// default to home page;
			return showHomePage(txt);
		} else if (Keywords.HOME_SHORTCUT.equalsIgnoreCase(keyword) && !StringUtil.isEmpty(txt.getCustomerState())) {

			return showHomePage(txt);

		} else if (keyword.equals(Keywords.TOPIC_SHORTCUT) || Keywords.QUESTION_SHORTCUT.equalsIgnoreCase(keyword)) {

			return questionService.gotoSelectTopic(txt);
		}

		else {
			// no keyword match
			String menuLevel = txt.getFromSession(ApplicationConstants.MENU_LEVEL);

			if (menuLevel != null) {
				if (MenuLevel.HOME.equals(menuLevel)) {
					return processHomeMenu(txt);
				} else if (MenuLevel.ADD_STUDENT.equals(menuLevel)) {
					return processAddStudent(txt);
				} else if (MenuLevel.CHANGE_GRADE.equals(menuLevel)) {
					return processChangeGrade(txt);
				} else if (MenuLevel.CREATE_QUIZ_GROUP.equals(menuLevel)) {
					return processCreateQuiz(txt);
				} else if (MenuLevel.INVITE_FRIENDS.equals(menuLevel)) {
					return processInviteFriends(txt);
				} else if (MenuLevel.QUESTION.equals(menuLevel)) {
					return processQuestion(txt);
				} else if (MenuLevel.SELECT_TOPIC.equals(menuLevel)) {
					return processTopicSelection(txt);
				} else if (MenuLevel.VIEW_QUIZ_RESULTS.equals(menuLevel)) {
					return viewQuizResults(txt);
				} else if (MenuLevel.SELECT_QUIZ_TOPICS.equals(menuLevel)) {
					return processCreateQuiz(txt);
				} else if (MenuLevel.AFTER_CREATE_QUIZ.equals(menuLevel)) {
					return processAfterCreateQuiz(txt);
				} else if (MenuLevel.INVITE_FRIENDS_TO_QUIZ.equals(menuLevel)) {
					return processInviteFriendsToQuiz(txt);
				} else if (MenuLevel.SELECT_QUIZ_STUDENT.equals(menuLevel)) {
					return processSelectQuizStudent(txt);
				} else if (MenuLevel.SELECT_STUDENT_OPTION.equalsIgnoreCase(menuLevel)) {
					return processStudentOption(txt);
				} else if (MenuLevel.SUBMIT_NAME.equalsIgnoreCase(menuLevel)) {
					return studentService.processStudentName(txt);
				} else if (MenuLevel.SUBMIT_GRADE.equalsIgnoreCase(menuLevel)) {
					return studentService.processStudentRegistration(txt);
				} else if (MenuLevel.ADD_STUDENT_NAME.equalsIgnoreCase(menuLevel)) {
					return studentService.processNewStudentName(txt);
				} else if (MenuLevel.ADD_STUDENT_SUBMIT_GRADE.equalsIgnoreCase(menuLevel)) {
					return studentService.processAddNewStudentRegistration(txt);
				} else if (MenuLevel.QUIZ_QUESTION.equalsIgnoreCase(menuLevel)) {
					return processQuizQuestion(txt);
				} else if (MenuLevel.SELECT_QUIZ.equalsIgnoreCase(menuLevel)) {
					return showQuizResults(txt);
				} else if (MenuLevel.SELECT_SUBJECT.equalsIgnoreCase(menuLevel)) {
					return selectSubject(txt);
				}else if (MenuLevel.SELECT_ASSIGNMENT.equalsIgnoreCase(menuLevel)) {
					return selectAssignment(txt);
				}else if (MenuLevel.END_CONCEPT_QUIZ.equalsIgnoreCase(menuLevel)) {
					return processAfterQuizEnd(txt);
				}

				txt.setMessage(Messages.get(ApplicationConstants.INVALID_REQUEST_TEMPLATE_KEY));
				txt.clearSessionState();
				return txt;
			} else {
				Quiz quiz = quizFacade.findQuizByCode(keyword);
				if (quiz == null) {
					return showInvalidInputPage(txt);
				} else {
					return quizService.quizRequest(txt, null);
				}
			}

		}
		// return txt;
	}

	private Txt processAfterQuizEnd(Txt txt) {
		String response = txt.getToken(0);
		if(response != null && response.equalsIgnoreCase("N")){
			return questionService.gotoNextQuiz(txt);
		}else{
			return showHomePage(txt);
		}
	}

	private Txt selectAssignment(Txt txt) {
		String response = txt.getToken(0);
		String quizId = txt.getFromSession(response);
		if (quizId != null) {
			
			return quizService.startAssignment(txt,quizId);
		} else {
			return showAssignments(txt, true);
		}
	}

	private Txt selectSubject(Txt txt) {
		String response = txt.getToken(0);
		String subjectId = txt.getFromSession(response);
		if (subjectId != null) {
			txt.setSubjectId(Long.parseLong(subjectId));
			return showHomePage(txt);
		} else {
			return showSubjects(txt, true);
		}

	}

	private Txt showSubjects(Txt txt, boolean invalidInput) {
		List<Subject> subjects = subjectFacade.findAllSubjects();
		// if there is one subject select it for student
		if (subjects.size() == 1) {
			txt.setSubjectId(subjects.get(0).getId());
			return showHomePage(txt);
		} else {
			return showSubjects(subjects, txt, invalidInput);
		}

	}

	private Txt showQuizResults(Txt txt) {

		return quizService.showQuizResults(txt);
	}

	private Txt processQuizQuestion(Txt txt) {

		return quizService.getQuizQuestion(txt);
	}

	private Txt processSelectQuizStudent(Txt txt) {

		return quizService.selectQuizStudent(txt);
	}

	private Txt processInviteFriendsToQuiz(Txt txt) {

		return quizService.shareWithFriends(txt);
	}

	private Txt processStudentOption(Txt txt) {
		String firstToken = txt.getToken(0);
		String selectionOptions = txt.getFromSession(ApplicationConstants.STUDENT_OPTIONS);
		// System.out.println("1>>>>>>>>>>>>>>>>>>>>="+selectionOptions);
		// System.out.println("2>>>>>>>>>>>>>>>>>>>>="+selectionOptions);
		// System.out.println("3>>>>>>>>>>>>>>>>>>>>="+selectionOptions);
		// System.out.println("Session>>>>>>>>>>>>>>>>>>>>>"+txt.getSessionState());
		Map<String, String> studentOptions = OptionsUtil.getOptionsMap(selectionOptions);

		String studentId = studentOptions.get(firstToken);
		txt.setStudentId(StringUtil.parseLong(studentId));
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.HOME);
		showHomePage(txt);

		return txt;
	}

	private Txt processAfterCreateQuiz(Txt txt) {

		return quizService.processAfterCreate(txt);
	}

	private Txt showInvalidInputPage(Txt txt) {
		/*
		 * String msg =
		 * Messages.get(ApplicationConstants.INVALID_INPUT_HOME_KEY);
		 * txt.setMessage(msg); txt.clearSessionState();
		 * txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.HOME);
		 * return txt;
		 */

		String msg = "";
		msg = Messages.getSmsMsg(ApplicationConstants.INVALID_INPUT_KEY);

		txt.setMessage(msg);
		txt.clearSessionState();
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.HOME);
		return txt;
	}

	private Txt viewQuizResults(Txt txt) {
		// TODO Auto-generated method stub
		return null;
	}

	private Txt processTopicSelection(Txt txt) {
		return questionService.processSelectTopic(txt);
	}

	private Txt processQuestion(Txt txt) {

		return questionService.processAnswer(txt);
	}

	private Txt processInviteFriends(Txt txt) {
		return notificationService.shareWithFriends(txt);
	}

	private Txt processCreateQuiz(Txt txt) {

		return quizService.createQuiz(txt);
	}

	private Txt processChangeGrade(Txt txt) {
		return studentService.changeStudentGrade(txt);
	}

	private Txt processAddStudent(Txt txt) {

		return studentService.addStudent(txt);
	}

	private Txt processHomeMenu(Txt txt) {
		String input = txt.getToken(0);

		Map<String, String> optionsMap = OptionsUtil.getOptionsMap(Messages.get(ApplicationConstants.HOME_MENU_OPTIONS));
		String selectedOption = optionsMap.get(input);
		if (selectedOption == null) {
			return showInvalidInputPage(txt);
		} else if (Keywords.QUESTIONS.equals(selectedOption)) {
			return questionService.gotoSelectTopic(txt);
		} else if (Keywords.RANKINGS.equalsIgnoreCase(selectedOption)) {
			return rankingService.getRankings(txt);
		} else if (Keywords.TRACK_PROGRESS.equalsIgnoreCase(selectedOption)) {
			return progressService.viewStudentProgress(txt);
		} else if (Keywords.ADD_STUDENT.equalsIgnoreCase(selectedOption)) {
			return studentService.viewAddStudentPage(txt);
		} else if (Keywords.CREATE_QUIZ.equalsIgnoreCase(selectedOption)) {
			return gotoCreateQuizMenu(txt);
		} else if (Keywords.QUIZ_RESULTS.equalsIgnoreCase(selectedOption)) {
			return gotoQuizResultsPage(txt);
		} else if (Keywords.CHANGE_GRADE.equalsIgnoreCase(selectedOption)) {
			return studentService.viewChangeGradeInfo(txt);
		} else if (Keywords.INVITE_FRIENDS.equalsIgnoreCase(selectedOption)) {
			return gotoInviteFriendsMenu(txt);
		} else if (Keywords.ASSIGNMENTS.equalsIgnoreCase(selectedOption)) {
			return showAssignments(txt,false);
		}
		return showInvalidInputPage(txt);
	}

	private Txt showAssignments(Txt txt,boolean invalidInput) {
		List<Assignment> assignments = assignmentFacade.findAssignmentsNotDoneByStudentId(txt.getStudentId(),txt.getSubjectId());
		
		System.out.println("****** assignments = " + assignments.size());
		
		if(assignments == null || assignments.size() == 0){
			String msg = Messages.get(ApplicationConstants.NO_ASSIGNMENTS_MESSAGE_KEY);
			txt.setMessage(msg);
			return txt;
		}
		
		String assignmentMenu,menu = "";
		if (!invalidInput) {
			assignmentMenu = Messages.get(ApplicationConstants.ASSIGNMENTS_MESSAGE_KEY);
		} else {
			assignmentMenu = Messages.get(ApplicationConstants.INVALID_ASSIGNMENTS_MESSAGE_KEY);
		}

		StringBuilder menuBuffer = new StringBuilder();
		int count = 1;

		for (Assignment assignment : assignments) {
			menuBuffer.append(count + "." + assignment.getAssignmentName() + "\n");
			txt.putInSession("" + count, "" + assignment.getQuizId());
			count++;
		}

		menu = assignmentMenu.replace("${assignments}", menuBuffer.toString());
		txt.setMessage(menu);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SELECT_ASSIGNMENT);
		return txt;
	}

	private Txt gotoInviteFriendsMenu(Txt txt) {
		return notificationService.gotoGeneralSharePage(txt);
	}

	private Txt gotoQuizResultsPage(Txt txt) {

		return quizService.gotoSelectQuiz(txt);
	}

	private Txt gotoCreateQuizMenu(Txt txt) {
		return quizService.gotoSelectQuizTopic(txt);
	}

	public Txt showHomePage(Txt txt) {
		int count = 0;
		String menuLevel = txt.getFromSession(ApplicationConstants.MENU_LEVEL);

		// check if subject is selected
		long subjectId = txt.getSubjectId();
		if (subjectId == 0) {
			// if not selected
			List<Subject> subjects = subjectFacade.findAllSubjects();
			// if there is one subject select it for student
			if (subjects.size() == 1) {
				txt.setSubjectId(subjects.get(0).getId());
			} else {
				return showSubjects(subjects, txt, false);
			}
		}

		if (MenuLevel.HOME.equalsIgnoreCase(menuLevel) || txt.getStudentId() != 0) {
			String studentId = txt.getFromSession(ApplicationConstants.STUDENT_ID);
			Long id = Long.parseLong(studentId);
			Student student = studentFacade.findStudentById(id);
			String msg = Messages.get(ApplicationConstants.HOME_MENU_KEY);
			txt.setFirstName(student.getFirstName());
			txt.setAcademicLevelId(student.getAcademicLevelId());
			txt.setStudentId(student.getId());
			
			Long assignments = assignmentFacade.findNumOfAssignmentsNotDoneByStudentId(txt.getStudentId(),txt.getSubjectId());
			txt.setMessage(msg.replace("Assignments", "Assignments("+ assignments+")"));
			
			
			/*
			 * String name =student.getFirstName(); name
			 * =StringUtil.trimName(name); name =StringUtil.toCamelCase(name);
			 * txt.setMessage(msg.replace("${studentName}", name));
			 */
			
			txt.clearSessionState();
			txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.HOME);

		} else {

			List<Student> students = studentFacade.findStudentByMobileNumber(txt.getMobileNumber());
			if (students != null && students.size() > 0) {
				// need to show option to select the appro
				count = students.size();
			}

			if (count == 1) {
				Student student = students.get(0);
				String msg = Messages.get(ApplicationConstants.HOME_MENU_KEY);
				txt.setFirstName(student.getFirstName());
				txt.setAcademicLevelId(student.getAcademicLevelId());
				txt.setStudentId(student.getId());
				/*
				 * String name =student.getFirstName(); name
				 * =StringUtil.trimName(name); name
				 * =StringUtil.toCamelCase(name);
				 * txt.setMessage(msg.replace("${studentName}", name));
				 */
				Long assignments = assignmentFacade.findNumOfAssignmentsNotDoneByStudentId(txt.getStudentId(),txt.getSubjectId());
				txt.setMessage(msg.replace("Assignments", "Assignments("+ assignments+")"));
				
				txt.clearSessionState();
				txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.HOME);

			} else if (count > 1) {
				// return menu for selecting the appropriate student
				// System.out.println("Getting student options menu interesting");
				txt = getStudentOptionsMenu(students, txt);
				return txt;

			} else if (count == 0) {

				// txt.setMessage(Messages.get(ApplicationConstants.REG_HELP));
				txt.clearSessionState();
				return txt;

			}

		}

		return txt;
	}

	private Txt showSubjects(List<Subject> subjects, Txt txt, boolean invalidInput) {
		String menu = "";

		// List<Topic> topics =
		// topicFacade.findTopicsByAcademicLevel(academicLevelId);
		String subjectsMenu = "";
		if (!invalidInput) {
			subjectsMenu = Messages.get(ApplicationConstants.SUBJECTS_MESSAGE_KEY);
		} else {
			subjectsMenu = Messages.get(ApplicationConstants.INVALID_SUBJECTS_MESSAGE_KEY);
		}

		StringBuilder menuBuffer = new StringBuilder();
		int count = 1;

		for (Subject subject : subjects) {
			menuBuffer.append(count + "." + subject.getName() + "\n");
			txt.putInSession("" + count, "" + subject.getId());
			count++;
		}

		menu = subjectsMenu.replace("${subjects}", menuBuffer.toString());
		txt.setMessage(menu);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SELECT_SUBJECT);
		return txt;
	}

	private Txt getStudentOptionsMenu(List<Student> students, Txt txt) {
		Map<String, String> studentMap = new TreeMap<String, String>();
		String response = "";
		int count = 1;
		StringBuilder msg = new StringBuilder();
		Formatter formatter = new Formatter(msg, Locale.US);
		// formatter.format("%1$2s%2$-21s%3$3s%n","", "","Pts");
		formatter.format("%1$36s%n", Messages.getSmsMsg(ApplicationConstants.STUDENT_OPTIONS_MENU));
		for (Student student : students) {
			String firstName = student.getFirstName();
			firstName = StringUtil.trimName(firstName);
			firstName = StringUtil.toCamelCase(firstName);
			formatter.format("%1$2s%2$-10s%n", count + ".", firstName);
			studentMap.put(Integer.toString(count), Long.toString(student.getId()));
			// System.out.println(">>>>>>>> count "+count+
			// ">>>>>>>>>>>id  "+student.getId());
			++count;
		}

		response = msg.toString();
		String studentOptions = OptionsUtil.convertAttributesMapToString(studentMap);
		// System.out.println("Select options>>>>>>>>>"+studentOptions);
		txt.putInSession(ApplicationConstants.STUDENT_OPTIONS, studentOptions);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SELECT_STUDENT_OPTION);
		txt.setMessage(response);
		// System.out.println("Whats is the set of options>>>>>>\n"+response);
		return txt;
	}

}
