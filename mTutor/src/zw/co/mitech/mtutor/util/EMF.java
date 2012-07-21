package zw.co.mitech.mtutor.util;

import java.io.Serializable;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("transactions-optional");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
