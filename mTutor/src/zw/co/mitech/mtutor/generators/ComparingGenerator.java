package zw.co.mitech.mtutor.generators;

import zw.co.mitech.mtutor.entities.QuizQuestion;
import zw.co.mitech.mtutor.service.AcademicLevelConstants;
import zw.co.mitech.mtutor.util.StringUtil;

public class ComparingGenerator implements QstnGenerator {
	
	private String academicLevel;
	private int diffLevel;

	@Override
	public QuizQuestion generateQuestion() {
		if(AcademicLevelConstants.GRADE_ONE.equals(academicLevel)){
			return generateGradeOneQuestion();
		}
		
		return null;
	}
	
	private QuizQuestion generateGradeOneQuestion() {
		QuizQuestion question = new QuizQuestion();
		int a =0;
		int b= 0;
		int points = 0;
		if(diffLevel == 0){
			
			a = StringUtil.generateRandomInt(0, 5);
			b = StringUtil.generateRandomInt(0, 5);
		    points = 1;
			
		}else if(diffLevel == 1){
			a = StringUtil.generateRandomInt(0, 5);
			b = StringUtil.generateRandomInt(5, 10);
		    points = 1;

		}else if(diffLevel == 2){
			a = StringUtil.generateRandomInt(0, 10);
			b = StringUtil.generateRandomInt(10, 20);
			points = 2;

		}else{
			diffLevel = 0;
			return generateGradeOneQuestion();
		}
		
		
		StringBuilder buffer = new StringBuilder("Which words make this statement true?\n" + a +" ___ " + b + "\nA. is greater than\nB. is less than\nC. is equal to");
		if(a == b){
			question.setExpectedAnswer("C");
		}else  if(a>b){
			question.setExpectedAnswer("A");
		}else if(a<b){
			question.setExpectedAnswer("B");
		}
		//question.setDifficultyScale(diffLevel);
		question.setPoints(points);
		question.setQuestionDetail(buffer.toString());
		return question;
	}

	public String getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(String academicLevel) {
		this.academicLevel = academicLevel;
	}

	public int getDiffLevel() {
		return diffLevel;
	}

	public void setDiffLevel(int diffLevel) {
		this.diffLevel = diffLevel;
	}

	public ComparingGenerator(String academicLevel, int diffLevel) {
		super();
		this.academicLevel = academicLevel;
		this.diffLevel = diffLevel;
   }
}
	
