package zw.co.mitech.mtutor.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.Assignment;
import zw.co.mitech.mtutor.util.AssignmentStatus;


@Repository("assignmentDao")
@Transactional
public class AssignmentFacade extends AbstractFacade<Assignment>  {
	   

		@PersistenceContext
	    private EntityManager em;

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

	    public AssignmentFacade() {
	        super(Assignment.class);
	    }

		public List<Assignment> findAssignmentsNotDoneByStudentId(long studentId,long subjectId) {
			System.out.println("******** finding assignments =" + studentId  + " " + subjectId);
			Query query = em.createQuery(" SELECT a FROM Assignment a WHERE a.studentId =:studentId AND a.subjectId =:subjectId AND ( a.status =:not_started OR a.status =:in_progress ) ORDER BY a.dateCreated ASC");
			query.setParameter("studentId", studentId);
			query.setParameter("subjectId", subjectId);
			query.setParameter("not_started", AssignmentStatus.NOT_STARTED);
			query.setParameter("in_progress", AssignmentStatus.IN_PROGRESS);
			return query.getResultList();
		}
		
		public Long findNumOfAssignmentsNotDoneByStudentId(long studentId,long subjectId) {
			Query query = em.createQuery(" SELECT COUNT(a) FROM Assignment a WHERE a.studentId =:studentId AND a.subjectId =:subjectId AND ( a.status =:not_started OR a.status =:in_progress ) ");
			query.setParameter("studentId", studentId);
			query.setParameter("subjectId", subjectId);
			query.setParameter("not_started", AssignmentStatus.NOT_STARTED);
			query.setParameter("in_progress", AssignmentStatus.IN_PROGRESS);
			return (Long) query.getSingleResult();
		}
}
