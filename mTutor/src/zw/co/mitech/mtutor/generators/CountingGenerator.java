package zw.co.mitech.mtutor.generators;

import zw.co.mitech.mtutor.entities.QuizQuestion;
import zw.co.mitech.mtutor.service.AcademicLevelConstants;
import zw.co.mitech.mtutor.util.StringUtil;

public class CountingGenerator implements QstnGenerator {
	
	private String academicLevel;
	private int diffLevel;
	public static String[] tokens = new String[]{"#","$","*","@", "+", "%"};
	
	
	@Override
	public QuizQuestion generateQuestion() {
		if(AcademicLevelConstants.GRADE_ONE.equals(academicLevel)){
			return generateGradeOneQuestion();
		}
		return null;
	}


	private QuizQuestion generateGradeOneQuestion() {
		QuizQuestion question = new QuizQuestion();
		int num =0;
		int points = 0;
		if(diffLevel == 0){
			
			num = StringUtil.generateRandomInt(1, 5);
		    points = 1;
			
		}else if(diffLevel == 1){
			num = StringUtil.generateRandomInt(5, 10);
		    points = 1;

		}else if(diffLevel == 2){
			num = StringUtil.generateRandomInt(10, 15);
		    points = 2;

		}else if(diffLevel == 3){
			num = StringUtil.generateRandomInt(15, 20);
		    points = 2;
		}else{
			diffLevel = 0;
			return generateGradeOneQuestion();
		}
		
		String token = getRandomToken();
		StringBuilder buffer = new StringBuilder("How many "+ token +" are there below ?\n\n");
		for(int i=1 ; i<= num ; i++){
			if(num <=40){
				buffer.append(token + " ");
				if(i%5 == 0){
					buffer.append("\n");
				}
			}else{
				buffer.append(token);
				if(i%10 == 0){
					buffer.append("\n");
				}
			}
		}
		buffer.append("\n");
		//question.setDifficultyScale(diffLevel);
		question.setExpectedAnswer(""+ num);
		question.setPoints(points);
		question.setQuestionDetail(buffer.toString());
		return question;
	}


	private String getRandomToken() {
		int max = tokens.length-1;
		int index = StringUtil.generateRandomInt(0,max);
		return tokens[index];
	}


	public CountingGenerator(String academicLevel, int diffLevel) {
		super();
		this.academicLevel = academicLevel;
		this.diffLevel = diffLevel;
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
	
	

}
