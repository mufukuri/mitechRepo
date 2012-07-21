/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.mitech.mtutor.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.Topic;

/**
 *
 * @author douglas
 */
@Repository("topicDao")
@Transactional
public class TopicFacade extends AbstractFacade<Topic> {
 
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TopicFacade() {
        super(Topic.class);
    }

	

	public List<Topic> findTopicsByAcademicLevel(long academicLevel) {
		String sql = "SELECT t FROM Topic t WHERE t.academicLevelId= :academicLevel ORDER BY t.topicName ASC ";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("academicLevel", academicLevel);
		return query.getResultList();
	}

	public List<Topic> findTopicsByGrade(Long id) {
		
		String sql = "SELECT t FROM Topic t WHERE t.academicLevelId= :academicLevel ORDER BY t.topicName ASC ";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("academicLevel", id);
		return query.getResultList();
	}

	
    
}
