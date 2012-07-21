package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.ConceptQuestionCounter;
import zw.co.mitech.mtutor.entities.QuizCounter;

@Repository
@Transactional
public class QuizCounterFacade  extends AbstractFacade<QuizCounter>  {
	
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	public QuizCounterFacade() {
		super(QuizCounter.class);
		
	}
	
	public QuizCounter getQuizCounter(long topic, long concept) {
		String sql = "SELECT q FROM QuizCounter q WHERE q.conceptId = :concept AND q.topicId = :topic";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("concept", concept);
		query.setParameter("topic", topic);
		QuizCounter qCounter= null;
		query.setMaxResults(1);
		try{
			qCounter= (QuizCounter) query.getSingleResult();
			
		}catch(NoResultException e){
			
			qCounter= null;
		}
		
		if(qCounter == null){
			qCounter = new QuizCounter();
			qCounter.setConceptId(concept);
			qCounter.setTopicId(topic);
			em.persist(qCounter);
		}
		
		return qCounter;
		
	}

	public void increment(QuizCounter quizCounter) {
		quizCounter.setCount(quizCounter.getCount()+1);
		em.merge(quizCounter);
	}


}
