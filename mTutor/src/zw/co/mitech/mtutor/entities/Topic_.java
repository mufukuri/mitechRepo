package zw.co.mitech.mtutor.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-16T04:47:37.080+0200")
@StaticMetamodel(Topic.class)
public class Topic_ {
	public static volatile SingularAttribute<Topic, Long> id;
	public static volatile SingularAttribute<Topic, String> topicName;
	public static volatile SingularAttribute<Topic, String> description;
	public static volatile SingularAttribute<Topic, String> topicType;
	public static volatile SingularAttribute<Topic, Integer> toUpgrade;
	public static volatile SingularAttribute<Topic, Date> dateCreated;
	public static volatile SingularAttribute<Topic, Long> subjectId;
	public static volatile SingularAttribute<Topic, Long> version;
	public static volatile SingularAttribute<Topic, Long> academicLevelId;
}
