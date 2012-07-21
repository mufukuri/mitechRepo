package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.StudentConcept;

@Repository
@Transactional
public class StudentConceptFacade  extends AbstractFacade<StudentConcept>  {
	
	
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	public StudentConceptFacade() {
		super(StudentConcept.class);
	}

	public StudentConcept findStudentConcept(long student, long conceptId) {
		
			String sql = "SELECT s FROM StudentConcept s WHERE s.studentId = :student AND s.conceptId =:conceptId";
			EntityManager em = getEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("student", student);
			query.setParameter("conceptId", conceptId);
		
		try{
			query.setMaxResults(1);
			return (StudentConcept) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}


}
