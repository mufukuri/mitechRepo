package zw.co.mitech.mtutor.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-16T04:47:36.821+0200")
@StaticMetamodel(Assignment.class)
public class Assignment_ {
	public static volatile SingularAttribute<Assignment, Long> id;
	public static volatile SingularAttribute<Assignment, Long> studentId;
	public static volatile SingularAttribute<Assignment, Long> quizId;
	public static volatile SingularAttribute<Assignment, Long> subjectId;
	public static volatile SingularAttribute<Assignment, String> assignmentName;
	public static volatile SingularAttribute<Assignment, Date> dateCreated;
	public static volatile SingularAttribute<Assignment, String> status;
	public static volatile SingularAttribute<Assignment, Long> version;
}
