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
 */
@Entity
@Table(name="Topic")
public class Topic implements Serializable, Persistable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topicName;
    private String description;
    private String topicType;
    // number of consecutive correct answers to upgrade to the next difficult scale
    private int toUpgrade;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;
    private long subjectId;
    @Version
 	private long version;
  
    private long academicLevelId;

    

    public Topic() {
		super();
		dateCreated = new Date(); 
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zw.co.mitech.mtutor.Topic[ id=" + getId() + " ]";
    }

    /**
     * @return the topicName
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * @param topicName the topicName to set
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

	public long getAcademicLevelId() {
		return academicLevelId;
	}

	public void setAcademicLevelId(long academicLevelId) {
		this.academicLevelId = academicLevelId;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public int getToUpgrade() {
		return toUpgrade;
	}

	public void setToUpgrade(int toUpgrade) {
		this.toUpgrade = toUpgrade;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
    
	
	
}
