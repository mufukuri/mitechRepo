package zw.co.mitech.mtutor.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.StudentConcept;
import zw.co.mitech.mtutor.entities.StudentSubject;
import zw.co.mitech.mtutor.util.Rank;

@Repository("studentSubjectDao")
@Transactional
public class StudentSubjectFacade extends AbstractFacade<StudentSubject> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public StudentSubjectFacade() {
		super(StudentSubject.class);
	}

	public StudentSubject findStudentSubject(long studentId, long subjectId,long academicLevelId) {
		String sql = "SELECT s FROM StudentSubject s WHERE s.studentId = :student AND s.subjectId =:subjectId AND s.academicLevelId =:academicLevelId ";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("student", studentId);
		query.setParameter("subjectId", subjectId);
		query.setParameter("academicLevelId", academicLevelId);
		try {
			query.setMaxResults(1);
			return (StudentSubject) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Rank> getStudentRankings(long academicLevelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SET @rank = 1, @prev_val = NULL, @prev_rank = NULL;");
		sql.append("SELECT @rank :=IF(@prev_val != points,@prev_rank+1,@rank) AS rank, @prev_val :=points AS points, @prev_rank :=@rank AS prevRank, t.studentId, t.firstName, t.lastName ");
		sql.append("FROM ( ");
			sql.append(" SELECT studentId, sc.points as points,firstName,lastName");
			sql.append(" FROM StudentConcept sc INNER JOIN Student s ON sc.studentId = s.id");
			sql.append(" ORDER BY points DESC");
		sql.append(") AS t");
		//sql.append("where studentId =1");
		Query query = em.createNativeQuery(sql.toString(),Rank.class);
		return query.getResultList();
	}

}
