package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.ConceptQuestionCounter;

@Repository("conceptCounterDao")
@Transactional
public class ConceptQuestionCounterFacade extends AbstractFacade<ConceptQuestionCounter>  {
	
	
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	public ConceptQuestionCounterFacade() {
		super(ConceptQuestionCounter.class);
	}

	public ConceptQuestionCounter findConceptQuestionCounter(long conceptId) {
		
		String sql = "SELECT q FROM ConceptQuestionCounter q WHERE q.conceptId = :conceptId";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("conceptId", conceptId);
		
		query.setMaxResults(1);
		try{
			ConceptQuestionCounter qCounter= (ConceptQuestionCounter) query.getSingleResult();
			return qCounter;
		}catch(Exception e){
			
			return null;
		}
	}
	
	
	
	
	
	public String getCounterName(String topicId, String academicLevelId) {
		
		return academicLevelId +"_"+topicId  ;
	}

	public ConceptQuestionCounter getQuestionCounter(long topic,long academicLevel, long concept) {
		String sql = "SELECT q FROM ConceptQuestionCounter q WHERE q.academicLevelId = :academicLevel AND q.conceptId = :concept AND q.topicId = :topic";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("academicLevel", academicLevel);
		query.setParameter("concept", concept);
		query.setParameter("topic", topic);
		ConceptQuestionCounter qCounter= null;
		query.setMaxResults(1);
		try{
			qCounter= (ConceptQuestionCounter) query.getSingleResult();
			
		}catch(NoResultException e){
			
			qCounter= null;
		}
		
		if(qCounter == null){
			qCounter = new ConceptQuestionCounter();
			qCounter.setAcademicLevelId(academicLevel);
			qCounter.setConceptId(concept);
			qCounter.setTopicId(topic);
			em.persist(qCounter);
		}
		
		return qCounter;
		
	}

	public void increment(ConceptQuestionCounter questionCounter) {
		questionCounter.setCount(questionCounter.getCount()+1);
		em.merge(questionCounter);
	}
	
}
