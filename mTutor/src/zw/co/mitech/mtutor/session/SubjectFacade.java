package zw.co.mitech.mtutor.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.Subject;

@Repository("subjectDao")
@Transactional
public class SubjectFacade extends AbstractFacade<Subject> {
	   
	
		@PersistenceContext
	    private EntityManager em;

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

		public SubjectFacade() {
			super(Subject.class);
		}

		public List<Subject> findAllSubjects() {
			Query query = em.createQuery("Select s FROM Subject s ORDER BY s.name ASC");
			List<Subject> subjects =  (List<Subject>)query.getResultList();
			return subjects;
		}

}
