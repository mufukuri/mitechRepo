package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.SchoolClass;
import zw.co.mitech.mtutor.entities.StudentTopic;
import zw.co.mitech.mtutor.util.ClassType;

@Repository
@Transactional
public class SchoolClassFacade extends AbstractFacade<SchoolClass>  {
	
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	public SchoolClassFacade() {
		super(SchoolClass.class);
	}
	
	public SchoolClass findGlobalClassByAcademicLevelId(long academicLevelId){
		String sql = "SELECT s FROM SchoolClass s WHERE s.academicLevelId = :academicLevelId AND s.classType = :classType";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("academicLevelId", academicLevelId);
		query.setParameter("classType", ClassType.GLOBAL);
		
		
		query.setMaxResults(1);
		try{
			return (SchoolClass) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}

}
