package zw.co.mitech.mtutor.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-16T04:47:37.002+0200")
@StaticMetamodel(StudentConcept.class)
public class StudentConcept_ {
	public static volatile SingularAttribute<StudentConcept, Long> id;
	public static volatile SingularAttribute<StudentConcept, Long> studentId;
	public static volatile SingularAttribute<StudentConcept, Long> conceptId;
	public static volatile SingularAttribute<StudentConcept, Long> correct;
	public static volatile SingularAttribute<StudentConcept, Long> wrong;
	public static volatile SingularAttribute<StudentConcept, Long> points;
	public static volatile SingularAttribute<StudentConcept, Long> total;
	public static volatile SingularAttribute<StudentConcept, Long> wrongHistory;
	public static volatile SingularAttribute<StudentConcept, Long> questionHistory;
	public static volatile ListAttribute<StudentConcept, Long> quizIds;
	public static volatile SingularAttribute<StudentConcept, Long> currentQuizId;
	public static volatile SingularAttribute<StudentConcept, Long> version;
}
