package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.Teacher;


@Repository
@Transactional
public class TeacherFacade  extends AbstractFacade<Teacher>  {
	
	
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	public TeacherFacade() {
		super(Teacher.class);
	}


}
