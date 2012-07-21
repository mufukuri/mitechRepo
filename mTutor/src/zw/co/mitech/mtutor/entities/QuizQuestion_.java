package zw.co.mitech.mtutor.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-16T04:47:36.950+0200")
@StaticMetamodel(QuizQuestion.class)
public class QuizQuestion_ {
	public static volatile SingularAttribute<QuizQuestion, Long> id;
	public static volatile SingularAttribute<QuizQuestion, String> questionDetail;
	public static volatile SingularAttribute<QuizQuestion, Long> questionNumber;
	public static volatile SingularAttribute<QuizQuestion, Long> conceptNumber;
	public static volatile SingularAttribute<QuizQuestion, String> expectedAnswer;
	public static volatile SingularAttribute<QuizQuestion, Long> academicLevelId;
	public static volatile SingularAttribute<QuizQuestion, String> detailedAnswer;
	public static volatile SingularAttribute<QuizQuestion, Long> topicId;
	public static volatile SingularAttribute<QuizQuestion, Long> conceptId;
	public static volatile SingularAttribute<QuizQuestion, Long> subjectId;
	public static volatile SingularAttribute<QuizQuestion, String> optionA;
	public static volatile SingularAttribute<QuizQuestion, String> optionA_Response;
	public static volatile SingularAttribute<QuizQuestion, String> optionB;
	public static volatile SingularAttribute<QuizQuestion, String> optionB_Response;
	public static volatile SingularAttribute<QuizQuestion, String> optionC;
	public static volatile SingularAttribute<QuizQuestion, String> optionC_Response;
	public static volatile SingularAttribute<QuizQuestion, String> optionD;
	public static volatile SingularAttribute<QuizQuestion, String> optionD_Response;
	public static volatile SingularAttribute<QuizQuestion, String> difficultyLevel;
	public static volatile SingularAttribute<QuizQuestion, Long> version;
	public static volatile SingularAttribute<QuizQuestion, Date> dateCreated;
	public static volatile SingularAttribute<QuizQuestion, Integer> points;
}
