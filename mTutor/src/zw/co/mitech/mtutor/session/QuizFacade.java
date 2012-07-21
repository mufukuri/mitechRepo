package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.ConceptQuestionCounter;
import zw.co.mitech.mtutor.entities.Quiz;
import zw.co.mitech.mtutor.entities.QuizCounter;
import zw.co.mitech.mtutor.entities.QuizQuestion;
import zw.co.mitech.mtutor.entities.TopicQuestionCounter;
import zw.co.mitech.mtutor.util.StringUtil;

@Repository("quizDao")
@Transactional
public class QuizFacade extends AbstractFacade<Quiz> {
	   
		@PersistenceContext
	    private EntityManager em;
		
		@Autowired
		private QuizCounterFacade quizCounterFacade;
	
	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

		public QuizFacade() {
			super(Quiz.class);
		}
		
		public Quiz findQuizByCode(String code) {
			String ql = "Select q FROM Quiz q WHERE q.code =:code";
			Query query = getEntityManager().createQuery(ql);
			query.setParameter("code", code);
			query.setMaxResults(1);
			try {
				Quiz quiz = (Quiz) query.getSingleResult();
				if(quiz != null){
					quiz.getQuestionIds();
				}
				return quiz;
			} catch (Exception e) {
				return null;
			}
		}
		
		public Quiz find(Object id) {
			String ql = "Select q FROM Quiz q WHERE q.id =:id";
			Query query = getEntityManager().createQuery(ql);
			query.setParameter("id", id);
			query.setMaxResults(1);
			try {
				Quiz quiz = (Quiz) query.getSingleResult();
				if(quiz != null){
					quiz.getQuestionIds();
				}
				return quiz;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public Quiz findQuiz(Long conceptId, String quizType, long quizNumber) {
			String ql = "Select q FROM Quiz q WHERE q.conceptId =:conceptId AND q.quizType =:quizType AND q.quizNumber =:quizNumber";
			Query query = getEntityManager().createQuery(ql);
			query.setParameter("conceptId", conceptId);
			query.setParameter("quizType", quizType);
			query.setParameter("quizNumber", quizNumber);
			
			query.setMaxResults(1);
			try {
				Quiz quiz = (Quiz) query.getSingleResult();
				if(quiz != null){
					quiz.getQuestionIds();
				}
				return quiz;
			} catch (Exception e) {
				return null;
			}
		}
	    
		
		public String generateQuizCode(){
			String code = "";
			Quiz existingQuiz = null;
			do {
				code = StringUtil.generateQuizCode();
				existingQuiz = findQuizByCode(code);

			} while (existingQuiz != null);
			return code;
		}
		
		
		public void create(Quiz quiz) {
			 
			 QuizCounter quizCounter = quizCounterFacade.getQuizCounter(quiz.getTopicId(),quiz.getConceptId());
			 
			
			 long count = quizCounter.getCount();
			 	
			 quiz.setQuizNumber(count +1);
			 
			 quizCounterFacade.increment(quizCounter);
			
			 getEntityManager().persist(quiz);
		 }

	    

}
