package zw.co.mitech.mtutor.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-20T14:32:52.869+0200")
@StaticMetamodel(SchoolClass.class)
public class SchoolClass_ {
	public static volatile SingularAttribute<SchoolClass, Long> id;
	public static volatile SingularAttribute<SchoolClass, String> joinCode;
	public static volatile SingularAttribute<SchoolClass, Long> teacherId;
	public static volatile SingularAttribute<SchoolClass, Long> academicLevelId;
	public static volatile SingularAttribute<SchoolClass, Long> schoolId;
	public static volatile SingularAttribute<SchoolClass, String> className;
	public static volatile SingularAttribute<SchoolClass, String> classType;
	public static volatile ListAttribute<SchoolClass, Long> subjectIds;
	public static volatile SingularAttribute<SchoolClass, Long> version;
}
