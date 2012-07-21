/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.mitech.mtutor.session;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.AcademicLevel;

/**
 *
 * @author douglas
 */
@Repository("levelDao")
@Transactional
public class AcademicLevelFacade extends AbstractFacade<AcademicLevel>  {
   

	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcademicLevelFacade() {
        super(AcademicLevel.class);
    }

	public AcademicLevel findAcademicLevelByGrade(String grade) {
		
		AcademicLevel level = null;
		try {
			String ql = "SELECT a FROM  AcademicLevel a WHERE a.levelName =:levelName";
  
			 Query q = em.createQuery(ql);
			 q.setParameter("levelName", grade);
			 level = (AcademicLevel) q.getSingleResult();
			 level.fetchParent(em);
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		
		return level;
		
		
		
	}

	public AcademicLevel findAcademicLevelByid(long academicLevel) {
		AcademicLevel level = null;
		try {
			String ql = "SELECT a FROM  AcademicLevel a WHERE a.id =:id";
  
			 Query q = em.createQuery(ql);
			 q.setParameter("id", academicLevel);
			 level = (AcademicLevel) q.getSingleResult();
			 level.fetchParent(em);
			
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return level;
		
	}
    
}
