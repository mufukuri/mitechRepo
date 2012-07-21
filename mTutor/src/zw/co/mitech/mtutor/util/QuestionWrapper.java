package zw.co.mitech.mtutor.util;

import javax.servlet.http.HttpServletRequest;

public class QuestionWrapper {
	private String questionDetail;
	private String answer;
	private String grade;
	private String topicId;
	private String difficultyLevel;
	public QuestionWrapper(HttpServletRequest request) {
		
		
		this.setQuestionDetail(request.getParameter("questionDetails"));
		//this.setMessage(req.getParameter("Message").toUpperCase());
		this.setAnswer(request.getParameter("answer"));
		this.setTopicId(request.getParameter("topicId"));
		this.setDifficultyLevel(request.getParameter("dificultyLevel"));
		
		
		
	}
	public String getQuestionDetail() {
		return questionDetail;
	}
	public void setQuestionDetail(String questionDetail) {
		this.questionDetail = questionDetail;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	

}
