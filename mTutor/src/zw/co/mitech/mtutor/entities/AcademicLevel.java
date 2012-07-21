/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.mitech.mtutor.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author douglas
 * 
 */
@Entity
@Table(name="AcademicLevel")
public class AcademicLevel implements Serializable, Persistable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String levelName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;
    @Version
    private long version;
    
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcademicLevel)) {
            return false;
        }
        AcademicLevel other = (AcademicLevel) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.levelName;
    }

    /**
     * @return the levelName
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * @param levelName the levelName to set
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

 


	@Override
	public void fetchParent(EntityManager em) {
		// TODO Auto-generated method stub
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
    
    
    
    
}
