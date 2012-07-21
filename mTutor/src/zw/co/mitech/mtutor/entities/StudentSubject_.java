package zw.co.mitech.mtutor.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-18T11:23:46.591+0200")
@StaticMetamodel(StudentSubject.class)
public class StudentSubject_ {
	public static volatile SingularAttribute<StudentSubject, Long> id;
	public static volatile SingularAttribute<StudentSubject, Long> studentId;
	public static volatile SingularAttribute<StudentSubject, Long> academicLevelId;
	public static volatile SingularAttribute<StudentSubject, Long> subjectId;
	public static volatile SingularAttribute<StudentSubject, String> topic;
	public static volatile SingularAttribute<StudentSubject, Long> wrong;
	public static volatile SingularAttribute<StudentSubject, Long> correct;
	public static volatile SingularAttribute<StudentSubject, Long> points;
	public static volatile SingularAttribute<StudentSubject, Long> total;
	public static volatile SingularAttribute<StudentSubject, Long> version;
}
