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
@Table(name="TopicMarker")
public class TopicMarker implements Serializable, Persistable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long studentId;
    private long topicId;
    private long conceptNumber;
    private long conceptId;
    private long quizId;
    private long quizNumber;
    @Version
 	private long version;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	public long getConceptNumber() {
		return conceptNumber;
	}
	public void setConceptNumber(long conceptNumber) {
		this.conceptNumber = conceptNumber;
	}
	public long getConceptId() {
		return conceptId;
	}
	public void setConceptId(long conceptId) {
		this.conceptId = conceptId;
	}
	public long getQuizId() {
		return quizId;
	}
	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}
	@Override
	public void fetchParent(EntityManager em) {
		// TODO Auto-generated method stub
		
	}
	public long getQuizNumber() {
		return quizNumber;
	}
	public void setQuizNumber(long quizNumber) {
		this.quizNumber = quizNumber;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
    
    
	
	

}
