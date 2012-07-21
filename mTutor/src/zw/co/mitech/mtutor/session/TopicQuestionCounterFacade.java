package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zw.co.mitech.mtutor.entities.TopicQuestionCounter;

@Repository("topicCounterDao")
@Transactional
public class TopicQuestionCounterFacade  extends AbstractFacade<TopicQuestionCounter>  {
	
	
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	public TopicQuestionCounterFacade() {
		super(TopicQuestionCounter.class);
	}

	public TopicQuestionCounter findTopicQuestionCounter(long topic) {
		
		String sql = "SELECT q FROM TopicQuestionCounter q WHERE q.topicId = :topic";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("topic", topic);
		
		query.setMaxResults(1);
		try{
			TopicQuestionCounter qCounter= new TopicQuestionCounter();
					//(QuizQuestionCounter) query.getSingleResult();
			return qCounter;
		}catch(Exception e){
			
			return null;
		}
	}
	
	
	
	
	
	public String getCounterName(String topicId, String academicLevelId) {
		
		return academicLevelId +"_"+topicId  ;
	}

	public TopicQuestionCounter getQuestionCounter(long topic,long academicLevel) {
		String sql = "SELECT q FROM TopicQuestionCounter q WHERE q.academicLevelId = :academicLevel AND q.topicId = :topic";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("academicLevel", academicLevel);
		query.setParameter("topic", topic);
		TopicQuestionCounter qCounter= null;
		query.setMaxResults(1);
		try{
			qCounter= (TopicQuestionCounter) query.getSingleResult();
			
		}catch(NoResultException e){
			
			qCounter= null;
		}
		
		if(qCounter == null){
			qCounter = new TopicQuestionCounter();
			qCounter.setAcademicLevelId(academicLevel);
			qCounter.setTopicId(topic);
			em.persist(qCounter);
		}
		
		return qCounter;
		
	}

	public void increment(TopicQuestionCounter questionCounter) {
		questionCounter.setCount(questionCounter.getCount()+1);
		em.merge(questionCounter);
	}

}
