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
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="StudentQuiz")
public class StudentQuiz  implements Serializable, Persistable {
	
	
	 private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private long quizId;
	 private long studentId;
	 private long correct;
	 private long wrong;
	 //current question
	 private long sequenceNumber;
	 private long total;
	 private long points;
	 private int rank;
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date startTime;
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date endTime;
	 private long totalTime;
	 
	 @Version
	 private long version;

	public StudentQuiz() {
		super();
		total = 0;
		points =0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void fetchParent(EntityManager em) {
		// TODO Auto-generated method stub
		
	}

	public long getQuizId() {
		return quizId;
	}

	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}

	public long getCorrect() {
		return correct;
	}

	public void setCorrect(long correct) {
		this.correct = correct;
	}

	public long getWrong() {
		return wrong;
	}

	public void setWrong(long wrong) {
		this.wrong = wrong;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	public long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		this.endTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		this.totalTime = this.endTime.getTime() - this.startTime.getTime();
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	

}
