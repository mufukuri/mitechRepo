package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.ConceptQuestionCounter;
import zw.co.mitech.mtutor.entities.QuizQuestion;
import zw.co.mitech.mtutor.entities.TopicQuestionCounter;

@Repository("quizQuestionDao")
@Transactional
public class QuizQuestionFacade  extends AbstractFacade<QuizQuestion>  {

	

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private TopicQuestionCounterFacade questionCounterFacade;
	
	@Autowired
	private ConceptQuestionCounterFacade conceptCounterFacade;


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	public QuizQuestionFacade() {
		super(QuizQuestion.class);
		
	}

	public QuizQuestion findQuizQuestion(long academicLevel,long topic, long qstnNumber) {
		
		String sql = "SELECT q FROM QuizQuestion q WHERE q.academicLevelId = :academicLevel AND q.topicId = :topic AND q.questionNumber =:questionNumber";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("academicLevel", academicLevel);
		query.setParameter("topic", topic);
		query.setParameter("questionNumber", qstnNumber);

		query.setMaxResults(1);
		try{
			return (QuizQuestion) query.getSingleResult();
		}catch(Exception e){
			
			return null;
		}
		
	}
	
	
	public QuizQuestion findQuizQuestionByConcept(long academicLevelId,long conceptId, long conceptNumber) {
		
		String sql = "SELECT q FROM QuizQuestion q WHERE q.academicLevelId = :academicLevelId AND q.conceptId = :conceptId AND q.conceptNumber =:conceptNumber";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("academicLevelId", academicLevelId);
		query.setParameter("conceptId", conceptId);
		query.setParameter("conceptNumber", conceptNumber);

		query.setMaxResults(1);
		try{
			return (QuizQuestion) query.getSingleResult();
		}catch(Exception e){
			
			return null;
		}
		
	}
    
	

	 public void create(QuizQuestion question) {
		 
		 TopicQuestionCounter questionCounter = questionCounterFacade.getQuestionCounter(question.getTopicId(),question.getAcademicLevelId());
		 ConceptQuestionCounter conceptCounter = conceptCounterFacade.getQuestionCounter(question.getTopicId(),question.getAcademicLevelId(),question.getConceptId());
		 
		
		 long count = questionCounter.getCount();
		 long conceptCount = conceptCounter.getCount();
			
		 question.setQuestionNumber(count +1);
		 question.setConceptNumber(conceptCount +1);
		 
		 questionCounterFacade.increment(questionCounter);
		 conceptCounterFacade.increment(conceptCounter);
		 
		 getEntityManager().persist(question);
	 }

    
}
