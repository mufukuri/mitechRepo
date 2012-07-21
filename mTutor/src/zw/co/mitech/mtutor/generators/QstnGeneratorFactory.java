package zw.co.mitech.mtutor.generators;

import zw.co.mitech.mtutor.util.GeneratedTopics;

public class QstnGeneratorFactory {
	
	public static QstnGenerator getQuestionGenerator(String topic,String academicLevel,int difficultyLevel){
		
		if(GeneratedTopics.Counting.toString().equals(topic)){
			return new CountingGenerator(academicLevel, difficultyLevel);
		}else if(GeneratedTopics.Addition.toString().equals(topic)){
			return new AdditionGenerator(academicLevel, difficultyLevel);
		}else if(GeneratedTopics.Subtraction.toString().equals(topic)){
			return new SubtractionGenerator(academicLevel, difficultyLevel);
		}else if(GeneratedTopics.Comparing.toString().equals(topic)){
			return new ComparingGenerator(academicLevel, difficultyLevel);
		}
		return null;
	}

}
