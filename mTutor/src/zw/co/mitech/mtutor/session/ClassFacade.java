package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.SchoolClass;

@Repository
@Transactional
public class ClassFacade  extends AbstractFacade<SchoolClass>  {
	
	
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	public ClassFacade() {
		super(SchoolClass.class);
	}


}
