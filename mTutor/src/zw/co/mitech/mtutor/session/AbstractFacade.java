package zw.co.mitech.mtutor.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.Persistable;

@Transactional
public abstract class AbstractFacade<T extends Persistable> {
	
	private Class<T> entityClass;
	   
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    //@Transactional(propagation=Propagation.REQUIRES_NEW)
    public void create(T entity) {
    	EntityManager em = getEntityManager();
    	
    	try{
    		em.persist(entity);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    		
    	
    }

    //@Transactional(propagation=Propagation.REQUIRES_NEW)
    public void edit(T entity) {
    	if(entity == null){
    		return;
    	}
    	EntityManager em = getEntityManager();
    	try{
    		em.merge(entity);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }

    //@Transactional(propagation=Propagation.REQUIRES_NEW)
    public void remove(T entity) {
    	EntityManager em = getEntityManager();
    	/*String ql = "DELETE FROM " + entityClass.getSimpleName()  + " e WHERE e.id =:id";
		 Query q = em.createQuery(ql);
		 q.setParameter("id", entity.getId());
		System.out.println("********** delete = " +  q.executeUpdate());*/
    	em.remove(entity);
    		
    }

    public T find(Object id) {
    	
        T x = getEntityManager().find(entityClass, id);
        x.fetchParent(getEntityManager());
        return x;
    }
    
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<T> findAll() {
    	EntityManager em = getEntityManager();
    	try{
    		String ql = "SELECT e FROM " + entityClass.getSimpleName()  + " e ";
    		 Query q = em.createQuery(ql);
    	     List<T> results = q.getResultList();
    	     for (T t : results) {
    			t.fetchParent(em);
    	     }
    	     return results;
    	}finally{
    		//em.close();
    	}
    }

    public int count() {
    	EntityManager em = getEntityManager();
    	try{
    		String ql = "SELECT COUNT(e) FROM " + entityClass.getSimpleName()  + " e ";
    		return (Integer)em.createQuery(ql).getSingleResult();
    	}finally{
    		//em.close();
    	}
    }

	
	
	 public List<T> findRange(int[] range) {
		 
		 EntityManager em = getEntityManager();
	    	
	     String ql = "SELECT e FROM " + entityClass.getSimpleName()  + " e ";

	     Query q = em.createQuery(ql);
	     q.setMaxResults(range[1] - range[0]);
	     q.setFirstResult(range[0]);
	     List<T> results = q.getResultList();
	     for (T t : results) {
			t.fetchParent(em);
	     }
	     return results;
	     
	 }
    
    
}
