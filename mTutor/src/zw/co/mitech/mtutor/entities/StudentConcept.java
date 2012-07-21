package zw.co.mitech.mtutor.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="StudentConcept")
public class StudentConcept  implements Serializable, Persistable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long studentId;
    private long conceptId;
    private long correct;
    private long wrong;
    private long points;
    private long total;
    private long wrongHistory;
    private long questionHistory;
    //quizIds of quizzes already taken by student
    @ElementCollection(fetch=FetchType.LAZY)
    private List<Long> quizIds;
    private long currentQuizId;
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
	public long getConceptId() {
		return conceptId;
	}
	public void setConceptId(long conceptId) {
		this.conceptId = conceptId;
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
	
	public List<Long> getQuizIds() {
		return quizIds;
	}
	public void setQuizIds(List<Long> quizIds) {
		this.quizIds = quizIds;
	}
	@Override
	public void fetchParent(EntityManager em) {
		// TODO Auto-generated method stub
		
	}
	
	public void incrementTotal(int value) {
		
		total = total + value;
		questionHistory  = questionHistory + 1;
	}
	
	
	public void incrementCorrect() {
		correct = correct + 1;
	}
	
	public void incrementWrong() {
		wrong = wrong + 1;
		wrongHistory = wrongHistory +1;
	}
	
	
	
	public void incrementPoints(int value) {
		points = points + value;
	}
	public long getWrongHistory() {
		return wrongHistory;
	}
	public void setWrongHistory(long wrongHistory) {
		this.wrongHistory = wrongHistory;
	}
	public long getQuestionHistory() {
		return questionHistory;
	}
	public void setQuestionHistory(long questionHistory) {
		this.questionHistory = questionHistory;
	}
	public long getCurrentQuizId() {
		return currentQuizId;
	}
	public void setCurrentQuizId(long currentQuizId) {
		this.currentQuizId = currentQuizId;
	}
	
	public void clearWrongHistory(){
		wrongHistory = 0;
		questionHistory = 0;
	}
	
	public boolean isDueForAnAssignment(){
		if(questionHistory < 10){
			return false;
		}else{
			if(wrongHistory >= 5){
				clearWrongHistory();
				return true;
			}else{
				clearWrongHistory();
				return false;
			}
		}
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
	
}
