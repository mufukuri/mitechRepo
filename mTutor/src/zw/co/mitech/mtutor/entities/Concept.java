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
@Table(name="Concept")
public class Concept  implements Serializable, Persistable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conceptName;
    private long topicId;
    //when this concept is introduced relative to other concepts in this topic
    private long topicSequence;
    @Version
    private long version;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConceptName() {
		return conceptName;
	}
	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	@Override
	public void fetchParent(EntityManager em) {
		// TODO Auto-generated method stub
		
	}
	public long getTopicSequence() {
		return topicSequence;
	}
	public void setTopicSequence(long topicSequence) {
		this.topicSequence = topicSequence;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
    
	
    
}
