/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.mitech.mtutor.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author douglas
 */
@Entity
@Table(name="Student")
public class Student implements Serializable, Persistable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private long points;
    private long total;
    private int totalCorrect;
    private int totalWrong;
    private String mobileNumber;
    private long academicLevelId;
    private int nameCount;
    private long schoolId;
    private long defaultClassId;
    @ElementCollection(fetch=FetchType.EAGER)
    private List<Long> classIds;
    @Version
 	private long version;

    @Temporal(TemporalType.TIMESTAMP)
   	private Date dateCreated;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zw.co.mitech.mtutor.Student[ id=" + getId() + " ]";
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the points
     */
    public long getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(long points) {
        this.points = points;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

   

    /**
     * @return the subject
     */
  /*  public Subject getSubject() {
        return subject;
    }

    *//**
     * @param subject the subject to set
     *//*
    public void setSubject(Subject subject) {
        this.subject = subject;
    }*/

	
	public void fetchParent(EntityManager em) {
		// TODO Auto-generated method stub
		
	}

	public int getTotalCorrect() {
		return totalCorrect;
	}

	public void setTotalCorrect(int totalCorrect) {
		this.totalCorrect = totalCorrect;
	}

	public int getTotalWrong() {
		return totalWrong;
	}

	public void setTotalWrong(int totalWrong) {
		this.totalWrong = totalWrong;
	}

	public long getAcademicLevelId() {
		return academicLevelId;
	}

	public void setAcademicLevelId(long academicLevelId) {
		this.academicLevelId = academicLevelId;
	}

	public void incrementPoints(int value) {
		points = points + value;
	}
	
	public void incrementTotal(int value) {
		total = total + value;
	}
	
	public void incrementCorrect() {
		totalCorrect = totalCorrect + 1;
	}
	
	public void incrementWrong() {
		totalWrong = totalWrong + 1;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getNameCount() {
		return nameCount;
	}

	public void setNameCount(int nameCount) {
		this.nameCount = nameCount;
	}

	public long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}

	public List<Long> getClassIds() {
		return classIds;
	}

	public void setClassIds(List<Long> classIds) {
		this.classIds = classIds;
	}

	public long getDefaultClassId() {
		return defaultClassId;
	}

	public void setDefaultClassId(long defaultClassId) {
		this.defaultClassId = defaultClassId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	
	
}
