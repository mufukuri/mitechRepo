package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.StudentTopic;
import zw.co.mitech.mtutor.entities.TopicMarker;

@Repository("topicMarkerDao")
@Transactional
public class TopicMarkerFacade extends AbstractFacade<TopicMarker> {
	 
		@PersistenceContext
	    private EntityManager em;

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

	    public TopicMarkerFacade() {
	        super(TopicMarker.class);
	    }

		public TopicMarker findStudentTopicMarker(long student,long topic) {
			
			String sql = "SELECT t FROM TopicMarker t WHERE t.studentId = :student AND t.topicId = :topic";
			EntityManager em = getEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("student", student);
			query.setParameter("topic", topic);
			
			
			query.setMaxResults(1);
			try{
				return (TopicMarker) query.getSingleResult();
			}catch(Exception e){
				return null;
			}
		}

}
