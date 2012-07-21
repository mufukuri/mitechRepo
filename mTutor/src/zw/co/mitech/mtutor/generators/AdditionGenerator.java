package zw.co.mitech.mtutor.generators;

import zw.co.mitech.mtutor.entities.QuizQuestion;
import zw.co.mitech.mtutor.service.AcademicLevelConstants;
import zw.co.mitech.mtutor.util.StringUtil;

public class AdditionGenerator implements QstnGenerator {
	
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
			
			a = StringUtil.generateRandomInt(1, 5);
			b = StringUtil.generateRandomInt(1, 5);
		    points = 1;
			
		}else if(diffLevel == 1){
			a = StringUtil.generateRandomInt(1, 5);
			b = StringUtil.generateRandomInt(5, 10);
		    points = 1;

		}else if(diffLevel == 2){
			a = StringUtil.generateRandomInt(5, 10);
			b = StringUtil.generateRandomInt(5, 10);
			points = 2;

		}else if(diffLevel == 3){
			a = StringUtil.generateRandomInt(1, 5);
			b = StringUtil.generateRandomInt(10,15);
		    points = 2;
		}else{
			diffLevel = 0;
			return generateGradeOneQuestion();
		}
		
		
		StringBuilder buffer = new StringBuilder("What is  "+ a +  "+" + b +" \n");
		int sum = a+b;
		//question.setDifficultyScale(diffLevel);
		question.setExpectedAnswer(""+ sum);
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

	public AdditionGenerator(String academicLevel, int diffLevel) {
		super();
		this.academicLevel = academicLevel;
		this.diffLevel = diffLevel;
	}
	
	
}
