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
@Table(name="StudentSubject")
public class StudentSubject  implements Serializable, Persistable {
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private long studentId;
	 private long academicLevelId;
	 private long subjectId;
	 private String topic;
	 //total wrong questions
	 private long wrong;
	 private long correct;
	 //student points
	 private long points;
	 //max possible points
	 private long total;
	 /*private int difficultyScale;
	 */
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
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public long getWrong() {
		return wrong;
	}
	public void setWrong(long wrong) {
		this.wrong = wrong;
	}
	public long getCorrect() {
		return correct;
	}
	public void setCorrect(long correct) {
		this.correct = correct;
	}
	public long getPoints() {
		return points;
	}
	public void setPoints(long points) {
		this.points = points;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	public void incrementTotal(int value) {
		
		total = total + value;
	}
	
	
	public void incrementCorrect() {
		correct = correct + 1;
	}
	
	public void incrementWrong() {
		wrong = wrong + 1;
	}
	
	public void incrementPoints(int value) {
		points = points + value;
	}
	
	
	@Override
	public void fetchParent(EntityManager em) {
		// TODO Auto-generated method stub
		
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public long getAcademicLevelId() {
		return academicLevelId;
	}
	public void setAcademicLevelId(long academicLevelId) {
		this.academicLevelId = academicLevelId;
	}
	
	

}
