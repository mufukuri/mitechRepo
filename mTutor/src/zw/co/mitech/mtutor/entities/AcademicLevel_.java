package zw.co.mitech.mtutor.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-16T04:47:37.534+0200")
@StaticMetamodel(AcademicLevel.class)
public class AcademicLevel_ {
	public static volatile SingularAttribute<AcademicLevel, Long> id;
	public static volatile SingularAttribute<AcademicLevel, String> levelName;
	public static volatile SingularAttribute<AcademicLevel, Date> dateCreated;
	public static volatile SingularAttribute<AcademicLevel, Long> version;
}
