package zw.co.mitech.mtutor.service;

import java.io.Serializable;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zw.co.mitech.mtutor.entities.AcademicLevel;
import zw.co.mitech.mtutor.entities.Quiz;
import zw.co.mitech.mtutor.entities.SchoolClass;
import zw.co.mitech.mtutor.entities.Student;
import zw.co.mitech.mtutor.session.AcademicLevelFacade;
import zw.co.mitech.mtutor.session.QuizFacade;
import zw.co.mitech.mtutor.session.SchoolClassFacade;
import zw.co.mitech.mtutor.session.StudentFacade;
import zw.co.mitech.mtutor.util.ApplicationConstants;
import zw.co.mitech.mtutor.util.MenuLevel;
import zw.co.mitech.mtutor.util.Messages;
import zw.co.mitech.mtutor.util.OptionsUtil;
import zw.co.mitech.mtutor.util.StringUtil;
import zw.co.mitech.mtutor.util.Txt;


@Service("studentService")
public class StudentService implements Processor, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StudentFacade studentFacade;
	@Autowired
	private RequestProcessor requestProcessor;
	@Autowired
	private QuizFacade quizFacade;
	@Autowired
	private QuizService quizService;
	@Autowired
	private AcademicLevelFacade academicFacade;
	
	@Autowired
	private SchoolClassFacade schoolClassFacade;

	
	
	
	public Txt registerStudent(Txt txt){
		//txt.tokeniseMsg();
		String token = txt.getToken(0);
		
		if(token != null){
			Quiz quiz = quizFacade.findQuizByCode(token);
			if(quiz != null){
				txt.putInSession(ApplicationConstants.QUIZ_CODE, quiz.getCode());
			}
		}
		//txt.setMobileNumber(txt.getId());
		
		
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SUBMIT_NAME);
		
		String response = Messages.getSmsMsg(ApplicationConstants.HELLO_INFO_MESSAGE);
		
		txt.setMessage(response);
		return txt;
		
	}
	
	
	

	@Override
	public Txt processTxtRequest(Txt txt){
		try{
			txt.tokeniseTxtMsg();
			
			String reg = txt.getToken(0);
			String mobile = txt.getId();
			String studentName = txt.getToken(2);
			String grade = txt.getToken(3);
			
			if(!StringUtil.isEmpty(txt.getCustomerState())){
				String mobileInCustomerState=txt.getCustomerState();
				
				String response = Messages.getSmsMsg(ApplicationConstants.ALREADY_REGISTERED);
				
				txt.setMessage(response.replace("${studentName}", mobileInCustomerState));
				txt.clearSessionState();
				return txt;
			}
			if(grade == null){
				txt.setMessage(Messages.get(ApplicationConstants.INVALID_REGISTRATION_MESSAGE_KEY));
				txt.clearSessionState();
				return txt;
			}
			if(mobile == null){
				txt.setMessage(Messages.get(ApplicationConstants.INVALID_REGISTRATION_MESSAGE_KEY));
				txt.clearSessionState();
				return txt;
			}/*
			if(!StringUtil.isValidMobile(mobile)){
				txt.setMessage(Messages.get(ApplicationConstants.INVALID_MOBILE_NUMBER_MESSAGE_KEY));
				txt.clearSessionState();
				return txt;
			}
			*/if(!StringUtil.isValidGrade(grade)){
				txt.setMessage(Messages.get(ApplicationConstants.INVALID_GRADE_MESSAGE_KEY));
				txt.clearSessionState();
				return txt;
			}
			mobile = StringUtil.formatMobile(mobile);
			
			
			
			
			txt.setCustomerState(mobile);
			Student student = new Student();
			student.setMobileNumber(mobile);
			student.setFirstName(studentName);
			
			
	
			
			
			List<Student> students = studentFacade.findStudentByMobileNumber(mobile);
			Student existingStudent =null;
			if(students!=null && students.size()==1){
				existingStudent =students.get(0);
			}else if(students!=null && students.size() >1){
				return getStudentOptionsMenu(students, txt);
			}
			
			if(existingStudent == null){
				if(StringUtil.isValidGradeWord(grade)){
					grade = StringUtil.getNumber(grade);
				}
				AcademicLevel level= academicFacade.findAcademicLevelByGrade(grade);
				
				student.setAcademicLevelId(level.getId());
				SchoolClass klass = schoolClassFacade.findGlobalClassByAcademicLevelId(level.getId());
				student.setDefaultClassId(klass.getId());
				studentFacade.create(student);
				txt.setMobileNumber(student.getMobileNumber());
				return requestProcessor.showHomePage(txt);
				
				
			}else{
				txt.setCustomerState(existingStudent.getMobileNumber());
				txt.setMobileNumber(existingStudent.getMobileNumber());
				txt.setFirstName(existingStudent.getFirstName());
				txt.putInSession(ApplicationConstants.ACADEMIC_LEVEL_ID, Long.toString(existingStudent.getAcademicLevelId()));
				txt.putInSession(ApplicationConstants.STUDENT_NAME, existingStudent.getFirstName());
				txt.putInSession(ApplicationConstants.STUDENT_ID,""+ existingStudent.getId());
				//txt.setMessage(response.replace("${studentName}", mobile));
				txt.serialiseSession();
		
				String response = Messages.getSmsMsg(ApplicationConstants.ALREADY_REGISTERED);
				txt.setMessage(response.replace("${mobile}", mobile));
				return txt;
				//FIXME when going live
				/*String response = Messages.getSmsMsg(ApplicationConstants.ALREADY_REGISTRED_MESSAGE_KEY);
				txt.setMessage(response.replace("${mobile}", mobile));
				txt.clearSessionState();
				return txt;*/
			}
		}catch (Exception e) {
			e.printStackTrace();
			txt.setMessage(Messages.getSmsMsg(ApplicationConstants.INVALID_REGISTRATION_MESSAGE_KEY));
			txt.clearSessionState();
			return txt;
		}
	}


	
	private Txt getStudentOptionsMenu(List<Student> students,Txt txt) {
		Map<String,String> studentMap = new TreeMap<String,String>();
		String response ="";
		int count =1;
		StringBuilder msg = new StringBuilder();
		 Formatter formatter = new Formatter(msg, Locale.US);
		 //formatter.format("%1$2s%2$-21s%3$3s%n","", "","Pts");
		 formatter.format("%1$36s%n", Messages.getSmsMsg(ApplicationConstants.STUDENT_OPTIONS_MENU));
		for(Student student : students){
			String firstName =student.getFirstName();
			 firstName=StringUtil.trimName(firstName);
			 firstName =StringUtil.toCamelCase(firstName);
		 formatter.format("%1$2s%2$-10s%n",count+".", firstName);
		 studentMap.put(Integer.toString(count), Long.toString(student.getId()));
		 ++count;
		}
		
		response = msg.toString();
		String studentOptions =OptionsUtil.convertAttributesMapToString(studentMap);
		txt.putInSession(ApplicationConstants.STUDENT_OPTIONS, studentOptions);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SELECT_STUDENT_OPTION);
		txt.setMessage(response);
		return txt;
	}

	
	
	public Txt addStudent(Txt txt) {
		txt.tokeniseTxtMsg();
		
		
		String studentName = txt.getToken(0);
		String grade = txt.getToken(1);
		String mobileNumber = txt.getCustomerState();
		
		
		if(studentName == null || StringUtil.isEmpty(studentName)){
			txt.setMessage(Messages.get(ApplicationConstants.INVALID_REGISTRATION_MESSAGE_KEY));
			txt.clearSessionState();
			return txt;
		}
		
		if(grade == null){
			txt.setMessage(Messages.get(ApplicationConstants.INVALID_REGISTRATION_MESSAGE_KEY));
			txt.clearSessionState();
			return txt;
		}
		if(!StringUtil.isValidGrade(grade)){
			txt.setMessage(Messages.get(ApplicationConstants.INVALID_GRADE_MESSAGE_KEY));
			txt.clearSessionState();
			return txt;
		}
		Student newStudent =  new Student();
		newStudent.setMobileNumber(mobileNumber);
		newStudent.setFirstName(studentName);
		
		if(StringUtil.isValidGradeWord(grade)){
			grade = StringUtil.getNumber(grade);
		}
		AcademicLevel level= academicFacade.findAcademicLevelByGrade(grade);
		
		newStudent.setAcademicLevelId(level.getId());
		
		studentFacade.create(newStudent);
		
		String response =Messages.getSmsMsg(ApplicationConstants.NEW_STUDENT_ADDED_INFO);
		response = response.replace("${mobile}", mobileNumber);
		studentName =StringUtil.toCamelCase(studentName);
		response = response.replace("${studentName}", studentName);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.HOME);
		txt.setMessage(response);
		return txt;
	}


	public Txt changeStudentGrade(Txt txt) {
		System.out.println("changing student grade");
		txt.tokeniseTxtMsg();
		String newGrade = txt.getToken(0);
		if(newGrade == null){
			txt.setMessage(Messages.getSmsMsg(ApplicationConstants.INVALID_GRADE_CHANGE_MESSAGE));
			//txt.clearSessionState();
			return txt;
		}
		
		if(!StringUtil.isValidGrade(newGrade)){
			txt.setMessage(Messages.getSmsMsg(ApplicationConstants.INVALID_GRADE_CHANGE_MESSAGE));
			//txt.clearSessionState();
			return txt;
		}
		
		long studentId = txt.getStudentId();
		
		Student student = studentFacade.findStudentById(studentId);
		
		if(student!=null){
			String firstName = student.getFirstName();
			firstName=StringUtil.trimName(firstName);
			firstName=StringUtil.toCamelCase(firstName);
			if(StringUtil.isValidGradeWord(newGrade)){
				newGrade = StringUtil.getNumber(newGrade);
			}
			AcademicLevel level= academicFacade.findAcademicLevelByGrade(newGrade);
			
			student.setAcademicLevelId(level.getId());
			studentFacade.edit(student);
			String message =Messages.getSmsMsg(ApplicationConstants.GRADE_CHANGE_MESSAGE);
			message =message.replace("${studentName}",firstName); 
			message =message.replace("${grade}",newGrade); 
			txt.setMessage(message);
		}
		return txt;
	}

	
	public Txt viewChangeGradeInfo(Txt txt){
		
		long studentId = txt.getStudentId();
		
		Student student = studentFacade.findStudentById(studentId);
		
		if(student!=null){
			long academicLevel = student.getAcademicLevelId();
			
			AcademicLevel level = academicFacade.findAcademicLevelByid(academicLevel);
			String currentGrade =level.getLevelName();
			String firstName = student.getFirstName();
			firstName=StringUtil.trimName(firstName);
			firstName=StringUtil.toCamelCase(firstName);
			txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.CHANGE_GRADE);
			String response =Messages.getSmsMsg(ApplicationConstants.CHANGE_GRADE_INFO);
			response =response.replace("${studentName}", firstName);
			response = response.replace("${currentGrade}",currentGrade);
			txt.setMessage(response);
			
		}
		
		return txt;
	}


	public Txt viewAddStudentPage(Txt txt) {
		long studentId = txt.getStudentId();
		
		Student student = studentFacade.findStudentById(studentId);
		
		if(student!=null){
			String firstName = student.getFirstName();
			firstName=StringUtil.trimName(firstName);
			firstName=StringUtil.toCamelCase(firstName);
		String msg = Messages.getSmsMsg(ApplicationConstants.ADD_STUDENT_MENU);
		msg = msg.replace("${studentName}", firstName);
		txt.setMessage(msg);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.ADD_STUDENT_NAME);
		}
		return txt;
	}


	public Txt gotToAddStudentPage(Txt txt) {
		long studentId = txt.getStudentId();
		
		Student student = studentFacade.findStudentById(studentId);
		
		if(student!=null){
			String firstName = student.getFirstName();
			firstName=StringUtil.trimName(firstName);
			firstName=StringUtil.toCamelCase(firstName);
		String msg = Messages.getSmsMsg(ApplicationConstants.ADD_STUDENT_MENU);
		msg = msg.replace("${studentName}", firstName);
		txt.setMessage(msg);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.ADD_STUDENT);
		}
		return txt;
	}

	public Txt processStudentName(Txt txt) {
		
		String studentName = txt.getToken(0);
		// check if name exists if it does increment it
		
		
		
		if(StringUtil.isEmpty(studentName)){
			String response = Messages.get(ApplicationConstants.HELLO_INFO_MESSAGE);
			txt.setMessage(response);
			return txt;
		}
		//Map<String,String> grades =StringUtil.getListofGrades();
		
		String gradeOptions =StringUtil.getGradeOptions();
		String response =Messages.getSmsMsg(ApplicationConstants.GRADE_INFO_MESSAGE);
		
		studentName =StringUtil.toCamelCase(studentName);
		
		response = response.replace("${validGrades}", gradeOptions);
		response = response.replace("${studentName}", studentName);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.SUBMIT_GRADE);
		txt.setFirstName(studentName);
		txt.setMessage(response);
		return txt;
	}

	/*
	private String checkIfStudentNameExists(String studentName){
		Student checkStudent = studentFacade.getStudentByName(studentName);
		if(checkStudent != null){
			String checkedName = 
		}
	}*/



	


	public Txt processStudentRegistration(Txt txt) {
		
		
		String grade = txt.getToken(0);
		

		if(StringUtil.isEmpty(grade)){
			String response = Messages.get(ApplicationConstants.GRADE_INFO_MESSAGE);
			txt.setMessage(response);
			return txt;
		}
		if(!StringUtil.isValidGrade(grade)){
			String resp =Messages.get(ApplicationConstants.INVALID_GRADE_INFO_MESSAGE);
			resp =resp.replace("${invalidGrade}", grade);
			txt.setMessage(resp);
			return txt;
		}
		
		if(StringUtil.isValidGradeWord(grade)){
			grade = StringUtil.getNumber(grade);
		}
		String studentName = txt.getFromSession(ApplicationConstants.STUDENT_NAME);
		
		int nameCount =checkifNameExists(studentName);
		
		
		Student newStudent = new Student();
		newStudent.setMobileNumber(txt.getCustomerState());
		studentName = studentName.toLowerCase();
		newStudent.setFirstName(studentName);
		newStudent.setNameCount(nameCount);
		System.out.println("txt first Name>>>>>>>"+txt.getFirstName());
		AcademicLevel level= academicFacade.findAcademicLevelByGrade(grade);
		
		newStudent.setAcademicLevelId(level.getId());
		long academicLevelId =level.getId();
		studentFacade.create(newStudent);
		
		String response =Messages.getSmsMsg(ApplicationConstants.HOME_MENU_KEY);
		
		
		
		studentName =StringUtil.toCamelCase(studentName);
		response = response.replace("${studentName}", studentName);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.HOME);
		txt.setAcademicLevelId(academicLevelId);
		txt.setStudentId(newStudent.getId());
		txt.setMessage(response);
		
		String quizCode = txt.getFromSession(ApplicationConstants.QUIZ_CODE);
		if(quizCode != null){
			return quizService.quizRequest(txt, quizCode);
		}
		
		return txt;
	}




	private int checkifNameExists(String studentName) {
	
		List<Student> students = studentFacade.getStudentByName(studentName);
		
		if(students!=null && students.size() == 0){
			return 0;
		}else if(students!=null && students.size()>0){
			
			Student student = students.get(0);
			int currentCounter =student.getNameCount();
			
			++currentCounter;
			
			return currentCounter;
		}
		return 0;
	}




	public Txt viewStudentGradeInfo(Txt txt) {

		return null;
	}




	public Txt processNewStudentName(Txt txt) {
		int count =1;
		txt.tokeniseMsg();
		String studentName = txt.getToken(0);
		// check if name exists if it does increment it
		
		
		
		if(StringUtil.isEmpty(studentName)){
			String response = Messages.get(ApplicationConstants.HELLO_INFO_MESSAGE);
			txt.setMessage(response);
			return txt;
		}
		//Map<String,String> grades =StringUtil.getListofGrades();
		
		String gradeOptions =StringUtil.getGradeOptions();
		String response =Messages.getSmsMsg(ApplicationConstants.ADD_STUDENT_GRADE_INFO_MESSAGE);
		
		studentName =StringUtil.toCamelCase(studentName);
		
		response = response.replace("${validGrades}", gradeOptions);
		response = response.replace("${studentName}", studentName);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.ADD_STUDENT_SUBMIT_GRADE);
		txt.putInSession(ApplicationConstants.NEW_STUDENT_NAME, studentName);
		
		txt.setMessage(response);
		return txt;
	}




	public Txt processAddNewStudentRegistration(Txt txt) {
		txt.tokeniseTxtMsg();
		
		String grade = txt.getToken(0);
		

		if(StringUtil.isEmpty(grade)){
			String response = Messages.get(ApplicationConstants.GRADE_INFO_MESSAGE);
			txt.setMessage(response);
			return txt;
		}
		if(!StringUtil.isValidGrade(grade)){
			String resp =Messages.get(ApplicationConstants.INVALID_GRADE_INFO_MESSAGE);
			resp =resp.replace("invalidGrade", grade);
			txt.setMessage(resp);
			//txt.clearSessionState();
			return txt;
		}
		
		if(StringUtil.isValidGradeWord(grade)){
			grade = StringUtil.getNumber(grade);
		}
		String studentName = txt.getFromSession(ApplicationConstants.NEW_STUDENT_NAME);
		Student newStudent = new Student();
		int nameCount =checkifNameExists(studentName);
		newStudent.setMobileNumber(txt.getId());
		newStudent.setNameCount(nameCount);
		studentName = studentName.toLowerCase();
		newStudent.setFirstName(studentName);
		AcademicLevel level= academicFacade.findAcademicLevelByGrade(grade);
		
		newStudent.setAcademicLevelId(level.getId());
		String academicLevelId =""+level.getId();
		studentFacade.create(newStudent);
		
		String response =Messages.getSmsMsg(ApplicationConstants.ADD_STUDENT_SUCCESS_INFO);
		
		studentName =StringUtil.toCamelCase(studentName);
		response = response.replace("${studentName}", studentName);
		response = response.replace("${grade}", grade);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.HOME);
		
		txt.setMessage(response);

		return txt;
	}
	
	
	
	
	
	
}
