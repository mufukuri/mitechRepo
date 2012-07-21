package zw.co.mitech.mtutor.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-16T04:47:36.989+0200")
@StaticMetamodel(Student.class)
public class Student_ {
	public static volatile SingularAttribute<Student, Long> id;
	public static volatile SingularAttribute<Student, String> firstName;
	public static volatile SingularAttribute<Student, String> lastName;
	public static volatile SingularAttribute<Student, Integer> age;
	public static volatile SingularAttribute<Student, Long> points;
	public static volatile SingularAttribute<Student, Long> total;
	public static volatile SingularAttribute<Student, Integer> totalCorrect;
	public static volatile SingularAttribute<Student, Integer> totalWrong;
	public static volatile SingularAttribute<Student, String> mobileNumber;
	public static volatile SingularAttribute<Student, Long> academicLevelId;
	public static volatile SingularAttribute<Student, Integer> nameCount;
	public static volatile SingularAttribute<Student, Long> schoolId;
	public static volatile SingularAttribute<Student, Long> defaultClassId;
	public static volatile ListAttribute<Student, Long> classIds;
	public static volatile SingularAttribute<Student, Long> version;
	public static volatile SingularAttribute<Student, Date> dateCreated;
}
