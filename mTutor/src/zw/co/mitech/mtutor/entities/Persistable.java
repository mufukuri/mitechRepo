package zw.co.mitech.mtutor.entities;

import javax.persistence.EntityManager;


public interface Persistable {
	
	public Long getId();
	public void fetchParent(EntityManager em);

}
