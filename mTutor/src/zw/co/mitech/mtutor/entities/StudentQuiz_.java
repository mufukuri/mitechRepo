package zw.co.mitech.mtutor.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-16T04:47:37.022+0200")
@StaticMetamodel(StudentQuiz.class)
public class StudentQuiz_ {
	public static volatile SingularAttribute<StudentQuiz, Long> id;
	public static volatile SingularAttribute<StudentQuiz, Long> quizId;
	public static volatile SingularAttribute<StudentQuiz, Long> studentId;
	public static volatile SingularAttribute<StudentQuiz, Long> correct;
	public static volatile SingularAttribute<StudentQuiz, Long> wrong;
	public static volatile SingularAttribute<StudentQuiz, Long> sequenceNumber;
	public static volatile SingularAttribute<StudentQuiz, Long> total;
	public static volatile SingularAttribute<StudentQuiz, Long> points;
	public static volatile SingularAttribute<StudentQuiz, Integer> rank;
	public static volatile SingularAttribute<StudentQuiz, Date> startTime;
	public static volatile SingularAttribute<StudentQuiz, Date> endTime;
	public static volatile SingularAttribute<StudentQuiz, Long> totalTime;
	public static volatile SingularAttribute<StudentQuiz, Long> version;
}
