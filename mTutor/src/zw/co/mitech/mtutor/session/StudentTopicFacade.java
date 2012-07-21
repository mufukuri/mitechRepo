package zw.co.mitech.mtutor.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.StudentTopic;

@Repository("studentTopicDao")
@Transactional
public class StudentTopicFacade  extends AbstractFacade<StudentTopic> {
	   
		
		@PersistenceContext
	    private EntityManager em;

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

		public StudentTopicFacade() {
			super(StudentTopic.class);
		}
		
		public StudentTopic findStudentTopic(long student, long topic) {
			
			String sql = "SELECT s FROM StudentTopic s WHERE s.studentId = :student AND s.topicId = :topic";
			EntityManager em = getEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("student", student);
			query.setParameter("topic", topic);
			
			
			query.setMaxResults(1);
			try{
				return (StudentTopic) query.getSingleResult();
			}catch(Exception e){
				return null;
			}
		}

		public List<StudentTopic> getStudentTopicsByStudentId(long studentId,long subjectId) {
	
			List<StudentTopic>  studentTopics = new ArrayList<StudentTopic>();
			
	    	try {
				 String ql = "SELECT s FROM  StudentTopic s WHERE s.studentId =:studentId AND s.subjectId =:subjectId ORDER BY s.topic ASC";
				 EntityManager em = getEntityManager();
				
				 Query q = em.createQuery(ql);
				 q.setParameter("studentId", studentId);
				 q.setParameter("subjectId", subjectId);
				 
				 studentTopics = q.getResultList();
				 
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    	
	    	return studentTopics;
		}
	    
}
