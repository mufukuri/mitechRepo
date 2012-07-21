package zw.co.mitech.mtutor.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-20T14:32:52.686+0200")
@StaticMetamodel(Quiz.class)
public class Quiz_ {
	public static volatile SingularAttribute<Quiz, Long> id;
	public static volatile SingularAttribute<Quiz, Long> studentId;
	public static volatile SingularAttribute<Quiz, Date> expiryDate;
	public static volatile SingularAttribute<Quiz, String> code;
	public static volatile SingularAttribute<Quiz, Long> topicId;
	public static volatile SingularAttribute<Quiz, Long> version;
	public static volatile SingularAttribute<Quiz, Long> conceptId;
	public static volatile SingularAttribute<Quiz, Long> subjectId;
	public static volatile SingularAttribute<Quiz, String> questions;
	public static volatile SingularAttribute<Quiz, String> quizType;
	public static volatile SingularAttribute<Quiz, Long> quizNumber;
	public static volatile SingularAttribute<Quiz, Long> teacherId;
}
