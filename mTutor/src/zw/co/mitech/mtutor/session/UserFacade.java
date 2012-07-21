/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.mitech.mtutor.session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.User;

/**
 *
 * @author douglas
 */
@Repository("userDao")
@Transactional
public class UserFacade extends AbstractFacade<User> {
   
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
}
