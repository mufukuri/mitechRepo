package zw.co.mitech.mtutor.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-16T04:47:36.855+0200")
@StaticMetamodel(Concept.class)
public class Concept_ {
	public static volatile SingularAttribute<Concept, Long> id;
	public static volatile SingularAttribute<Concept, String> conceptName;
	public static volatile SingularAttribute<Concept, Long> topicId;
	public static volatile SingularAttribute<Concept, Long> topicSequence;
	public static volatile SingularAttribute<Concept, Long> version;
}
