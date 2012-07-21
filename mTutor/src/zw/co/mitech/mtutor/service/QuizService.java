package zw.co.mitech.mtutor.service;

import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zw.co.mitech.mtutor.entities.AcademicLevel;
import zw.co.mitech.mtutor.entities.Assignment;
import zw.co.mitech.mtutor.entities.Concept;
import zw.co.mitech.mtutor.entities.ConceptQuestionCounter;
import zw.co.mitech.mtutor.entities.Quiz;
import zw.co.mitech.mtutor.entities.QuizQuestion;
import zw.co.mitech.mtutor.entities.StudentSubject;
import zw.co.mitech.mtutor.entities.TopicQuestionCounter;
import zw.co.mitech.mtutor.entities.Student;
import zw.co.mitech.mtutor.entities.StudentConcept;
import zw.co.mitech.mtutor.entities.StudentQuiz;
import zw.co.mitech.mtutor.entities.StudentTopic;
import zw.co.mitech.mtutor.entities.Topic;
import zw.co.mitech.mtutor.session.AcademicLevelFacade;
import zw.co.mitech.mtutor.session.AssignmentFacade;
import zw.co.mitech.mtutor.session.ConceptFacade;
import zw.co.mitech.mtutor.session.ConceptQuestionCounterFacade;
import zw.co.mitech.mtutor.session.QuizFacade;
import zw.co.mitech.mtutor.session.RankingsFacade;
import zw.co.mitech.mtutor.session.StudentSubjectFacade;
import zw.co.mitech.mtutor.session.TopicQuestionCounterFacade;
import zw.co.mitech.mtutor.session.QuizQuestionFacade;
import zw.co.mitech.mtutor.session.StudentConceptFacade;
import zw.co.mitech.mtutor.session.StudentFacade;
import zw.co.mitech.mtutor.session.StudentQuizFacade;
import zw.co.mitech.mtutor.session.StudentTopicFacade;
import zw.co.mitech.mtutor.session.TopicFacade;
import zw.co.mitech.mtutor.util.ApplicationConstants;
import zw.co.mitech.mtutor.util.AssignmentStatus;
import zw.co.mitech.mtutor.util.DateUtil;
import zw.co.mitech.mtutor.util.EmailService;
import zw.co.mitech.mtutor.util.Keywords;
import zw.co.mitech.mtutor.util.MenuLevel;
import zw.co.mitech.mtutor.util.Messages;
import zw.co.mitech.mtutor.util.QuizType;
import zw.co.mitech.mtutor.util.Rank;
import zw.co.mitech.mtutor.util.StringUtil;
import zw.co.mitech.mtutor.util.Txt;

@Service("quizService")
public class QuizService {

	public static final int NUM_OF_QUIZ_QSTNS = 10;

	@Autowired
	private TopicFacade topicFacade;

	@Autowired
	private AcademicLevelFacade academicLevelFacade;

	@Autowired
	private QuizQuestionService questionService;

	@Autowired
	private StudentFacade studentFacade;

	@Autowired
	private QuizFacade quizFacade;

	@Autowired
	private StudentQuizFacade studentQuizFacade;

	@Autowired
	private TopicQuestionCounterFacade quizCounterFacade;

	@Autowired
	private ConceptQuestionCounterFacade conceptCounterFacade;

	@Autowired
	private QuizQuestionFacade quizQuestionFacade;
	@Autowired
	private StudentConceptFacade studentConceptFacade;
	@Autowired
	private StudentTopicFacade studentTopicFacade;
	@Autowired
	private ConceptFacade conceptFacade;
	@Autowired
	private AssignmentFacade assignmentFacade;
	@Autowired
	private StudentSubjectFacade studentSubjectFacade;
	@Autowired
	private RankingsFacade rankingFacade;
	

	public Txt gotoSelectQuizTopic(Txt txt) {
		long academicLevelId = txt.getAcademicLevelId();
		String menu = getQuizTopicsMenu(academicLevelId);
		txt.setMessage(menu);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SELECT_QUIZ_TOPICS);
		return txt;
	}

	public String getQuizTopicsMenu(long academicLevelId) {
		String menu = null;
		List<Topic> topics = topicFacade.findTopicsByAcademicLevel(academicLevelId);
		String qtsnTopicsMenu = Messages.get(ApplicationConstants.QUIZ_TOPICS_MESSAGE_KEY);

		StringBuilder menuBuffer = new StringBuilder();
		StringBuilder menuOptionBuffer = new StringBuilder();
		int count = 1;

		for (Topic topic : topics) {
			menuBuffer.append(count + "." + topic.getTopicName() + "\n");
			menuOptionBuffer.append(count + ":" + topic.getId() + ",");
			count++;
		}

		menuBuffer.append(count + "." + ApplicationConstants.ALL_TOPICS + "\n");
		menuOptionBuffer.append(count + ":" + ApplicationConstants.ALL_TOPICS_ID);

		menu = qtsnTopicsMenu.replace("${topics}", menuBuffer.toString());
		return menu;
	}

	public Txt createQuiz(Txt txt) {
		String response = txt.getToken(0);
		long academicLevelId = txt.getAcademicLevelId();
		Map<String, String> optionsMap = questionService.getTopicsMenuOptions(academicLevelId);
		String topicId = optionsMap.get(response);
		if (topicId == null) {
			String menu = getQuizTopicsMenu(academicLevelId);
			menu = "Invalid Response\n\n" + menu;
			txt.setMessage(menu);
			txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SELECT_QUIZ_TOPICS);
			return txt;
		}
		Quiz quiz = new Quiz();
		quiz.setExpiryDate(DateUtil.getEndOfDay(new Date()));
		quiz.setTopicId(Long.parseLong(topicId));
		Quiz existingQuiz = null;
		// generate quiz code
		do {
			String code = StringUtil.generateQuizCode();
			quiz.setCode(code);
			existingQuiz = quizFacade.findQuizByCode(code);

		} while (existingQuiz != null);
		long studentId = txt.getStudentId();
		quiz.setStudentId(studentId);
		if (ApplicationConstants.ALL_TOPICS_ID.equals(topicId)) {

			do {
				topicId = getRandomTopicId(academicLevelId);
				TopicQuestionCounter counter = quizCounterFacade.findTopicQuestionCounter(Long.parseLong(topicId));
				long count = counter.getCount();
				long qstnNumber = StringUtil.generateRandomlong(1, count);
				QuizQuestion question = quizQuestionFacade.findQuizQuestion(academicLevelId, Long.parseLong(topicId), qstnNumber);
				quiz.addQuestion(question.getId());
			} while (quiz.numberOfQuestions() < NUM_OF_QUIZ_QSTNS);

		} else {

			TopicQuestionCounter counter = quizCounterFacade.findTopicQuestionCounter(Long.parseLong(topicId));
			long count = counter.getCount();
			do {
				long qstnNumber = StringUtil.generateRandomlong(1, count);
				QuizQuestion question = quizQuestionFacade.findQuizQuestion(academicLevelId, Long.parseLong(topicId), qstnNumber);
				quiz.addQuestion(question.getId());
			} while (quiz.numberOfQuestions() < NUM_OF_QUIZ_QSTNS);

		}

		AcademicLevel academicLevel = academicLevelFacade.find(academicLevelId);
		String topic = "";
		if (ApplicationConstants.ALL_TOPICS_ID.equals(topicId)) {
			topic = ApplicationConstants.ALL_TOPICS;
		} else {
			topic = topicFacade.find(Long.parseLong(topicId)).getTopicName();
		}
		quizFacade.create(quiz);
		StudentQuiz studentQuiz = new StudentQuiz();
		studentQuiz.setQuizId(quiz.getId());
		studentQuiz.setStudentId(studentId);
		studentQuiz.setStartTime(new Date());
		studentQuiz.setSequenceNumber(0);
		studentQuizFacade.create(studentQuiz);
		String quizMenu = Messages.get(ApplicationConstants.QUIZ_MESSAGE_KEY);
		quizMenu = quizMenu.replace("${grade}", academicLevel.getLevelName());
		quizMenu = quizMenu.replace("${topic}", topic);
		quizMenu = quizMenu.replace("${code}", quiz.getCode());
		txt.setMessage(quizMenu);
		txt.putInSession(ApplicationConstants.QUIZ_ID, "" + quiz.getId());
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.AFTER_CREATE_QUIZ);

		return txt;
	}

	public String getRandomTopicId(long academicLevelId) {
		Map<String, String> topics = questionService.getTopicsMenuOptions(academicLevelId);
		int maxSize = topics.size();
		String topicId = "";
		do {
			int index = ((int) (Math.random() * 1000)) % maxSize;
			index = index + 1;
			topicId = topics.get("" + index);
		} while (ApplicationConstants.ALL_TOPICS_ID.equals(topicId));
		return topicId;
	}

	public Txt processAfterCreate(Txt txt) {
		String token = txt.getToken(0);
		if (Keywords.INVITE_FRIENDS_SHORTCUT.equalsIgnoreCase(token)) {
			return gotoInviteFriends(txt);
		} else if (Keywords.START_QUIZ_SHORTCUT.equalsIgnoreCase(token)) {
			return getQuizQuestion(txt);
		} else {
			long academicLevelId = txt.getAcademicLevelId();
			AcademicLevel academicLevel = academicLevelFacade.find(academicLevelId);
			String quizId = txt.getFromSession(ApplicationConstants.QUIZ_ID);
			Quiz quiz = quizFacade.find(Long.parseLong(quizId));
			String topicId = "" + quiz.getTopicId();
			String topic = "";
			if (ApplicationConstants.ALL_TOPICS_ID.equals(topicId)) {
				topic = ApplicationConstants.ALL_TOPICS;
			} else {
				topic = topicFacade.find(Long.parseLong(topicId)).getTopicName();
			}
			String quizMenu = Messages.get(ApplicationConstants.ERROR_QUIZ_MESSAGE_KEY);
			quizMenu = quizMenu.replace("${grade}", academicLevel.getLevelName());
			quizMenu = quizMenu.replace("${topic}", topic);
			quizMenu = quizMenu.replace("${code}", quiz.getCode());
			txt.setMessage(quizMenu);
			txt.putInSession(ApplicationConstants.QUIZ_ID, "" + quiz.getId());
			txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.AFTER_CREATE_QUIZ);
			return txt;
		}
	}

	public Txt getQuizQuestion(Txt txt) {
		String answer = txt.getToken(0);
		String quizId = txt.getFromSession(ApplicationConstants.QUIZ_ID);
		long studentId = txt.getStudentId();
		StudentQuiz studentQuiz = studentQuizFacade.findStudentQuizBy(Long.parseLong(quizId), studentId);
		String expectedAnswer = txt.getFromSession(ApplicationConstants.EXPECTED_ANSWER);
		Quiz quiz = quizFacade.find(Long.parseLong(quizId));
		if (studentQuiz.getStartTime() == null) {
			studentQuiz.setStartTime(new Date());
		}

		QuizQuestion question = null;
		StudentConcept studentConcept = null;
		StudentTopic studentTopic = null;
		StudentSubject studentSubject = null;
		String msgHeader = "";
		long nextQstnNumber = studentQuiz.getSequenceNumber() + 1;
		if (expectedAnswer != null) {

			String questionId = txt.getFromSession(ApplicationConstants.QUESTION_ID);
			question = quizQuestionFacade.find(Long.parseLong(questionId));
			studentConcept = questionService.getStudentConcept(txt.getStudentId(), question.getConceptId());
			studentTopic = questionService.getStudentTopic(txt.getStudentId(), question.getTopicId());
			studentSubject = questionService.getStudentSubject(txt.getStudentId(),txt.getSubjectId(),txt.getAcademicLevelId());
			
			int points = txt.getIntFromSession(ApplicationConstants.QUESTION_POINTS);

			studentQuiz.incrementTotal(points);
			studentConcept.incrementTotal(points);
			studentTopic.incrementTotal(points);
			studentSubject.incrementTotal(points);
			if (expectedAnswer.equalsIgnoreCase(answer)) {
				// correct answer
				studentQuiz.incrementCorrect();
				studentQuiz.incrementPoints(points);
				studentConcept.incrementCorrect();
				studentConcept.incrementPoints(points);
				studentTopic.incrementCorrect();
				studentTopic.incrementPoints(points);
				studentSubject.incrementCorrect();
				studentSubject.incrementPoints(points);
				
				msgHeader = Messages.get(ApplicationConstants.CORRECT_RESPONSE_KEY);
			} else {
				// wrong answer
				studentQuiz.incrementWrong();
				studentConcept.incrementWrong();
				studentTopic.incrementWrong();
				studentSubject.incrementWrong();
				
				msgHeader = Messages.get(ApplicationConstants.INCORRECT_RESPONSE_KEY);
				msgHeader = msgHeader.replace("${answer}", expectedAnswer + question.getExplanation(answer));
			}

		}
		studentQuiz.setSequenceNumber(nextQstnNumber);

		Long nextQstnId = quiz.getQuestionId(nextQstnNumber);
		if (nextQstnId != null) {

			question = quizQuestionFacade.find(nextQstnId);
			String qstn = question.getSmsMessage(nextQstnNumber);
			txt.setMessage(msgHeader + qstn);
			txt.putInSession(ApplicationConstants.EXPECTED_ANSWER, question.getExpectedAnswer());
			txt.putInSession(ApplicationConstants.QUESTION_ID, "" + question.getId());
			txt.putInSession(ApplicationConstants.QUESTION_POINTS, "" + question.getPoints());
			txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.QUIZ_QUESTION);
			
			studentQuizFacade.edit(studentQuiz);
			studentConceptFacade.edit(studentConcept);
			studentTopicFacade.edit(studentTopic);
			studentSubjectFacade.edit(studentSubject);
			
			return txt;
		} else {
			studentQuiz.setEndTime(new Date());

			if (studentConcept.isDueForAnAssignment()) {
				createAssignment(studentConcept, txt);
			}

			studentQuizFacade.edit(studentQuiz);
			studentConceptFacade.edit(studentConcept);
			studentTopicFacade.edit(studentTopic);
			studentSubjectFacade.edit(studentSubject);
			String msg = Messages.get(ApplicationConstants.END_QUIZ_MSG);
			Rank rank = rankingFacade.getStudentRankingByQuiz(txt.getStudentId(),quiz.getId());
			int numOfStudents = rankingFacade.getNumberOfStudentsThatTookQuiz(quiz.getId());
			
			msg = msg.replace("${score}", studentQuiz.getPoints() + "/" + studentQuiz.getTotal());
			msg = msg.replace("${rank}", ""+rank.getRank());
			msg = msg.replace("${numberOfStudents}", ""+numOfStudents);
			msg = msg.replace("${percentage}", StringUtil.getPercentage( studentQuiz.getPoints(), studentQuiz.getTotal()));
			
			txt.setMessage(msg);
			txt.clearSessionState();
			return txt;
		}

	}

	public void createAssignment(StudentConcept studentConcept, Txt txt) {
		
		Assignment assignment = new Assignment();
		assignment.setDateCreated(new Date());
		Concept concept = conceptFacade.find(studentConcept.getConceptId());
		assignment.setAssignmentName(concept.getConceptName());
		assignment.setStatus(AssignmentStatus.NOT_STARTED);
		assignment.setStudentId(txt.getStudentId());
		assignment.setSubjectId(txt.getSubjectId());

		Quiz quiz = new Quiz();
		quiz.setExpiryDate(DateUtil.getEndOfDay(new Date()));
		quiz.setTopicId(concept.getTopicId());
		quiz.setConceptId(concept.getId());
		Quiz existingQuiz = null;
		// generate quiz code
		do {
			String code = StringUtil.generateQuizCode();
			quiz.setCode(code);
			existingQuiz = quizFacade.findQuizByCode(code);

		} while (existingQuiz != null);
		
		ConceptQuestionCounter counter = conceptCounterFacade.findConceptQuestionCounter(studentConcept.getConceptId());
		long count = counter.getCount();
		do {
			long qstnNumber = StringUtil.generateRandomlong(1, count);
			QuizQuestion question = quizQuestionFacade.findQuizQuestionByConcept(txt.getAcademicLevelId(), studentConcept.getConceptId(), qstnNumber);
			quiz.addQuestion(question.getId());
		} while (quiz.numberOfQuestions() < NUM_OF_QUIZ_QSTNS);

		quiz.setQuizType(QuizType.ASSIGNMENT);
		quizFacade.create(quiz);
		assignment.setQuizId(quiz.getId());
		assignmentFacade.create(assignment);

	}

	


	private Txt gotoInviteFriends(Txt txt) {
		String msg = Messages.get(ApplicationConstants.INVITE_FRIENDS_FOR_QUIZ);
		txt.setMessage(msg);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.INVITE_FRIENDS_TO_QUIZ);
		return txt;
	}

	public Txt shareWithFriends(Txt txt) {

		String txtMsg = txt.getTxtMsg();
		String[] contacts = txtMsg.split(",");
		if (contacts == null || contacts.length == 0) {
			String msg = Messages.getSmsMsg(ApplicationConstants.INVALID_GENERAL_SHARE_HOME_KEY);
			// msg = StringUtil.insertUnreadMsgsCount(inboxMsgs, msg);
			txt.setMessage(msg);
			return txt;
		}

		boolean hasValidContacts = false;
		String quizId = txt.getFromSession(ApplicationConstants.QUIZ_ID);
		long studentId = txt.getStudentId();
		Quiz quiz = quizFacade.find(Long.parseLong(quizId));
		long academicLevelId = txt.getAcademicLevelId();
		AcademicLevel academicLevel = academicLevelFacade.find(academicLevelId);
		String topicId = "" + quiz.getTopicId();
		String topic = "";
		if (ApplicationConstants.ALL_TOPICS_ID.equals(topicId)) {
			topic = ApplicationConstants.ALL_TOPICS;
		} else {
			topic = topicFacade.find(Long.parseLong(topicId)).getTopicName();
		}
		for (String contact : contacts) {
			if (StringUtil.isValidEmail(contact)) {
				hasValidContacts = true;
				String quizMenu = Messages.get(ApplicationConstants.QUIZ_EMAIL_INVITE_MESSAGE_KEY);
				quizMenu = quizMenu.replace("${grade}", academicLevel.getLevelName());
				quizMenu = quizMenu.replace("${topic}", topic);
				quizMenu = quizMenu.replace("${code}", quiz.getCode());
				String to = contact;
				String subject = "Invitation to join quiz on mtutor";

				String msg = quizMenu;
				EmailService.sendEmail(to, msg, subject);
				// sendQuizShareEmail(txt.getMobileNumber(),contact,txt);
			} else if (StringUtil.isValidMobile(contact)) {
				hasValidContacts = true;
				String quizMenu = Messages.get(ApplicationConstants.QUIZ_SMS_INVITE_MESSAGE_KEY);
				quizMenu = quizMenu.replace("${grade}", academicLevel.getLevelName());
				quizMenu = quizMenu.replace("${topic}", topic);
				quizMenu = quizMenu.replace("${code}", quiz.getCode());
				sendQuizShareSms(txt.getMobileNumber(), contact, txt);
			}
		}

		if (!hasValidContacts) {
			String msg = Messages.get(ApplicationConstants.INVITE_FRIENDS_FOR_QUIZ);
			txt.setMessage("Invalid Input\n" + msg);
			txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.INVITE_FRIENDS_TO_QUIZ);
			return txt;

		} else {
			String msg = Messages.getSmsMsg(ApplicationConstants.AFTER_INVITING_FRIENDS);
			// msg = StringUtil.insertUnreadMsgsCount(inboxMsgs, msg);
			txt.setMessage(msg);
			txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.AFTER_CREATE_QUIZ);
			return txt;
		}

	}

	private void sendQuizShareSms(String mobileNumber, String contact, Txt txt) {
		// TODO Auto-generated method stub

	}

	private void sendQuizShareEmail(String mobileNumber, String contact, Txt txt) {
		// TODO Auto-generated method stub

	}

	public Txt quizRequest(Txt txt, String quizCode) {
		if (quizCode == null) {
			quizCode = txt.getToken(0);
		}
		long studentId = txt.getStudentId();
		if (studentId != 0) {
			return joinQuiz(quizCode, studentId, txt);
		} else {
			List<Student> students = studentFacade.findStudentByMobileNumber(txt.getMobileNumber());
			if (students.size() == 1) {
				Student student = students.get(0);
				studentId = student.getId();
				txt.setStudentId(studentId);
				return joinQuiz(quizCode, studentId, txt);
			} else {
				Quiz quiz = quizFacade.findQuizByCode(quizCode);
				String msg = Messages.get(ApplicationConstants.SELECT_STUDENT_FOR_QUIZ_MESSAGE);
				StringBuffer buffer = new StringBuffer();
				int count = 1;
				for (Student student : students) {
					buffer.append(count + "." + StringUtil.toCamelCase(student.getFirstName()) + "\n");
					txt.putInSession("" + count, "" + student.getId());
					count++;
				}
				msg = msg.replace("${students}", buffer.toString());
				txt.setMessage(msg);
				txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SELECT_QUIZ_STUDENT);
				txt.putInSession(ApplicationConstants.QUIZ_ID, "" + quiz.getId());
				txt.putInSession(ApplicationConstants.QUIZ_CODE, quiz.getCode());
				return txt;
			}
		}

	}

	public Txt joinQuiz(String quizCode, long studentId, Txt txt) {
		Quiz quiz = quizFacade.findQuizByCode(quizCode);
		StudentQuiz studentQuiz = studentQuizFacade.findStudentQuizBy(quiz.getId(), studentId);
		if (studentQuiz == null) {
			studentQuiz = new StudentQuiz();
			studentQuiz.setStartTime(new Date());
			studentQuiz.setStudentId(studentId);
			studentQuiz.setSequenceNumber(0);
			studentQuiz.setQuizId(quiz.getId());
			studentQuizFacade.create(studentQuiz);
		}
		txt.putInSession(ApplicationConstants.QUIZ_ID, "" + quiz.getId());
		return getQuizQuestion(txt);
	}

	public Txt selectQuizStudent(Txt txt) {
		String token = txt.getToken(0);
		String studentId = txt.getFromSession(token);
		String quizCode = txt.getFromSession(ApplicationConstants.QUIZ_CODE);
		if (studentId != null) {
			Student student = studentFacade.find(Long.parseLong(studentId));
			txt.setStudentId(Long.parseLong(studentId));
			txt.setAcademicLevelId(student.getAcademicLevelId());
			txt.setFirstName(student.getFirstName());
			txt.clearSessionState();
			return joinQuiz(quizCode, Long.parseLong(studentId), txt);
		} else {
			List<Student> students = studentFacade.findStudentByMobileNumber(txt.getMobileNumber());

			Quiz quiz = quizFacade.findQuizByCode(quizCode);
			String msg = Messages.get(ApplicationConstants.SELECT_STUDENT_FOR_QUIZ_MESSAGE);
			msg = "Invalid Input\n" + msg;
			StringBuffer buffer = new StringBuffer();
			int count = 1;
			for (Student student : students) {
				buffer.append(count + "." + StringUtil.toCamelCase(student.getFirstName()) + "\n");
				txt.putInSession("" + count, "" + student.getId());
				count++;
			}
			msg = msg.replace("${students}", buffer.toString());
			txt.setMessage(msg);
			txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SELECT_QUIZ_STUDENT);
			txt.putInSession(ApplicationConstants.QUIZ_ID, "" + quiz.getId());
			txt.putInSession(ApplicationConstants.QUIZ_CODE, quiz.getCode());
			return txt;
		}

	}

	public Txt gotoSelectQuiz(Txt txt) {
		long studentId = txt.getStudentId();
		List<StudentQuiz> studentQuizes = studentQuizFacade.findStudentQuizByStudentId(studentId);
		String msg = Messages.get(ApplicationConstants.SELECT_QUIZ);
		StringBuilder buffer = new StringBuilder();
		int count = 1;
		txt.clearSessionState();
		if (studentQuizes != null && studentQuizes.size() > 0) {
			for (StudentQuiz studentQuiz : studentQuizes) {
				Quiz quiz = quizFacade.find(studentQuiz.getQuizId());

				String quizTxt = count + "." + getQuizTxt(quiz, studentQuiz);

				String sms = msg.replace("${quiz}", buffer.toString() + quizTxt);
				if (sms.length() > 160) {
					break;
				} else {
					buffer.append(quizTxt + "\n");
					txt.putInSession("" + count, "" + quiz.getId());
					count++;
				}
			}
			msg = msg.replace("${quiz}", buffer.toString());
			txt.setMessage(msg);

			txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SELECT_QUIZ);
			return txt;
		} else {
			msg = Messages.get(ApplicationConstants.NO_QUIZ);
			txt.setMessage(msg);
			return txt;
		}
	}

	private String getQuizTxt(Quiz quiz, StudentQuiz studentQuiz) {

		return " " + quiz.getCode() + " - " + DateUtil.DDMMMYY.format(studentQuiz.getStartTime());
	}

	public Txt showQuizResults(Txt txt) {
		String token = txt.getToken(0);
		String quizId = txt.getFromSession(token);
		if (quizId == null) {
			return gotoSelectQuiz(txt);
		} else {
			String msg = Messages.get(ApplicationConstants.QUIZ_RESULTS);
			long studentId = txt.getStudentId();
			List<StudentQuiz> studentsQuiz = studentQuizFacade.rankStudents(Long.parseLong(quizId));
			int count = 1;
			StringBuilder buffer = new StringBuilder();
			Formatter formatter = new Formatter(buffer, Locale.US);
			for (StudentQuiz studentQ : studentsQuiz) {

				if (studentQ.getStudentId() == studentId) {
					msg = msg.replace("${score}", studentQ.getPoints() + "/" + studentQ.getTotal());
					msg = msg.replace("${rank}", count + "/" + studentsQuiz.size());
					msg = msg.replace("${time}", StringUtil.getTime(studentQ.getTotalTime()));
				}
				if (count <= 5) {

					Student student = studentFacade.find(studentQ.getStudentId());
					String firstName = student.getFirstName();
					int nameCounter = student.getNameCount();
					if (nameCounter != 0) {
						firstName = firstName + nameCounter;
					}
					firstName = StringUtil.toCamelCase(firstName);
					formatter.format("%1$2s%2$-11s%3$3s%n", count + ".", firstName, studentQ.getPoints() + "pts");
					// formatter.format(format, args)

				}
				count++;
			}
			msg = msg.replace("${rankings}", buffer.toString());
			txt.setMessage(msg);
			return txt;
		}

	}

	public Txt startAssignment(Txt txt, String quizId) {
		long studentId = txt.getStudentId();
		StudentQuiz studentQuiz = studentQuizFacade.findStudentQuizBy(Long.parseLong(quizId), studentId);
		if (studentQuiz == null) {
			studentQuiz = new StudentQuiz();
			studentQuiz.setStartTime(new Date());
			studentQuiz.setStudentId(studentId);
			studentQuiz.setSequenceNumber(0);
			studentQuiz.setQuizId(Long.parseLong(quizId));
			studentQuizFacade.create(studentQuiz);
		}
		txt.putInSession(ApplicationConstants.QUIZ_ID, quizId);
		return getQuizQuestion(txt);
	}
	
	
	

}
