package zw.co.mitech.mtutor.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="QuizCounter")
public class QuizCounter  implements Serializable, Persistable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long topicId;
	private long academicLevelId;
	private long count;
	private long conceptId;
	@Version
 	private long version;
	
	
	
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	public long getAcademicLevelId() {
		return academicLevelId;
	}
	public void setAcademicLevelId(long academicLevelId) {
		this.academicLevelId = academicLevelId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getConceptId() {
		return conceptId;
	}
	public void setConceptId(long conceptId) {
		this.conceptId = conceptId;
	}
	@Override
	public void fetchParent(EntityManager em) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
