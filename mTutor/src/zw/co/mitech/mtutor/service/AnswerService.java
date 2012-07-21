package zw.co.mitech.mtutor.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zw.co.mitech.mtutor.session.StudentFacade;
@Service("answerService")
public class AnswerService implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private StudentFacade studentFacade;

	
/*
	@Override
	public Txt processTxtRequest(Txt txt) {
		//String secondToken = txt.getToken(1);
		String firstToken = txt.getToken(0);
	
		if(StringUtil.isValidResponseLetter(firstToken)){
			if(ApplicationConstants.QUESTION_RESPONSE.equalsIgnoreCase(txt.getSessionState())){
				txt.deserialiseSession();
				
				String questionNumber = txt.getFromSession(ApplicationConstants.KEY_QUESTION_NUMBER);
				String mobileNumber = txt.getFromSession(ApplicationConstants.KEY_MOBILE_NUMBER);
				
				Student student = studentFacade.findStudentByMobileNumber(mobileNumber);
				System.out.println("Question number >>>>>>>>"+questionNumber);
				Answer answer = answerFacade.findAnswerByQuestionNumber(questionNumber);
				System.out.println("Answer did we get one >>>>>>>"+answer);
				System.out.println("Expectd answer is >>>>>>>"+answer.getExpectedAnswer());
				System.out.println("Token or sent answer>>>>>>"+firstToken);
				if(answer.getExpectedAnswer().equalsIgnoreCase(firstToken)){
					txt.setSessionState(ApplicationConstants.TO_HOME_PAGE);
					txt.setMessage(ApplicationConstants.CORRECT_RESPONSE+answer.getDetailedAnswer()+ApplicationConstants.VIEW_HOME_PAGE);
					long totalPoints =student.getPoints();
					totalPoints =totalPoints + answer.getPoints();
					int correct = student.getTotalCorrect();
					++correct;
					student.setTotalCorrect(correct);
					student.setPoints(totalPoints);
				
					
				}else{
					int totalWrong = student.getTotalWrong();
					++totalWrong;
					student.setTotalWrong(totalWrong);
					txt.setSessionState(ApplicationConstants.TO_HOME_PAGE);
					txt.setMessage(ApplicationConstants.INCORRECT_RESPONSE+answer.getDetailedAnswer()+ApplicationConstants.VIEW_HOME_PAGE);
				}
				studentFacade.edit(student);
			}
			
		}
		return txt;
	}*/

}
