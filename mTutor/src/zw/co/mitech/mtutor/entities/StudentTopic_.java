package zw.co.mitech.mtutor.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-20T14:32:52.925+0200")
@StaticMetamodel(StudentTopic.class)
public class StudentTopic_ {
	public static volatile SingularAttribute<StudentTopic, Long> id;
	public static volatile SingularAttribute<StudentTopic, Long> studentId;
	public static volatile SingularAttribute<StudentTopic, Long> topicId;
	public static volatile SingularAttribute<StudentTopic, String> topic;
	public static volatile SingularAttribute<StudentTopic, Long> subjectId;
	public static volatile SingularAttribute<StudentTopic, Long> wrong;
	public static volatile SingularAttribute<StudentTopic, Long> correct;
	public static volatile SingularAttribute<StudentTopic, Long> points;
	public static volatile SingularAttribute<StudentTopic, Long> total;
	public static volatile SingularAttribute<StudentTopic, Long> currentConceptId;
	public static volatile SingularAttribute<StudentTopic, Long> version;
}
