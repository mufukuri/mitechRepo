package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.School;

@Repository
@Transactional
public class SchoolFacade  extends AbstractFacade<School>  {
	
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	public SchoolFacade() {
		super(School.class);
	}


}
