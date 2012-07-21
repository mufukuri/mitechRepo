package zw.co.mitech.mtutor.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.StudentQuiz;

@Repository("studentQuizDao")
@Transactional
public class StudentQuizFacade extends AbstractFacade<StudentQuiz> {
	   
	
		@PersistenceContext
	    private EntityManager em;

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

		public StudentQuizFacade() {
			super(StudentQuiz.class);
		}

		public StudentQuiz findStudentQuizBy(long quiz, long student) {
			
			String sql = "SELECT s FROM StudentQuiz s WHERE s.studentId = :student AND s.quizId = :quiz";
			EntityManager em = getEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("student", student);
			query.setParameter("quiz", quiz);
			
			
			query.setMaxResults(1);
			try{
				StudentQuiz studentQuiz =(StudentQuiz) query.getSingleResult();
				return studentQuiz;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}

		public List<StudentQuiz> rankStudents(long quiz) {
			
			
			String sql = "SELECT s FROM StudentQuiz s WHERE s.quizId = :quiz ORDER BY s.points DESC,s.totalTime ASC";
			EntityManager em = getEntityManager();
			Query query = em.createQuery(sql);
			
			query.setParameter("quiz", quiz);
			return query.getResultList();
		}

		public List<StudentQuiz> findStudentQuizByStudentId(long student) {
			
			String sql = "SELECT s FROM StudentQuiz s WHERE s.studentId = :student ORDER BY s.startTime DESC";
			EntityManager em = getEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("student", student);
			try{
				return query.getResultList();
			}catch(Exception e){
				return null;
			}
		}
	    
	    

}
