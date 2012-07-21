package zw.co.mitech.mtutor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Basic;
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

@Entity
@Table(name="Quiz")
public class Quiz implements Serializable, Persistable {
	
	
		private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 	//quiz creator
	 	private long studentId;
	 	@Temporal(TemporalType.TIMESTAMP)
	 	private Date expiryDate;
	 	private String code;
	 	private long topicId;
	 	//quiz questions ids
	 	//@ElementCollection(fetch=FetchType.EAGER)
	 	private transient Set<Long> questionIds;
	 	@Version
	 	private long version;
	 	private long conceptId;
	 	private long subjectId;
	 	private String questions;
	
	 	//assignment,syllabus,class
	 	private String quizType;
	 	private long quizNumber;
	 	private long teacherId;
	 	
	 	
	 	
	 	

		

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



		public String getCode() {
			return code;
		}






		public void setCode(String code) {
			this.code = code;
		}






		public long getTopicId() {
			return topicId;
		}






		public void setTopicId(long topicId) {
			this.topicId = topicId;
		}


		public Set<Long> getQuestionIds() {
			if(questionIds == null && questions != null){
				String[] qstns = questions.split(",");
				questionIds =  new TreeSet<Long>();
				for (String qstn : qstns) {
					try{
						questionIds.add(Long.parseLong(qstn));
					}catch(Exception e){
						
					}
				}
			}
			return questionIds;
		}


		public void setQuestionIds(Set<Long> questionIds) {
			
			this.questionIds = questionIds;
			StringBuffer buffer = new StringBuffer();
			int count = 1;
			for (Long qstn : questionIds) {
				if(count != 1){
					buffer.append(",");
				}
				buffer.append(qstn);
				count++;
			}
			questions = buffer.toString();
		}


		


		@Override
		public void fetchParent(EntityManager em) {
			this.getQuestionIds();
			//this.getStudentIds();
			
		}






		public Date getExpiryDate() {
			return expiryDate;
		}






		public void setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
		}






		
		public void addQuestion(Long question){
			if(questionIds == null){
				questionIds = new TreeSet<Long>();
			}
			if(questions == null){
				questions = ""+question;
			}else{
				questions = questions + ","+question;
			}
			questionIds.add(question);
		}






		public int numberOfQuestions() {
			if(getQuestionIds() == null){
				return 0;
			}else{
				return getQuestionIds().size();
			}
		}






		public Long getQuestionId(long nextQstnNumber) {
			if(getQuestionIds() == null){
				return null;
			}
			ArrayList<Long> ids = new ArrayList<Long>(getQuestionIds());
			try{
				return ids.get((int) (nextQstnNumber -1));
			}catch(Exception e){
				return null;
			}
		}






		public long getConceptId() {
			return conceptId;
		}






		public void setConceptId(long conceptId) {
			this.conceptId = conceptId;
		}






		public long getSubjectId() {
			return subjectId;
		}






		public void setSubjectId(long subjectId) {
			this.subjectId = subjectId;
		}






		public String getQuizType() {
			return quizType;
		}






		public void setQuizType(String quizType) {
			this.quizType = quizType;
		}


		public long getQuizNumber() {
			return quizNumber;
		}

		public void setQuizNumber(long quizNumber) {
			this.quizNumber = quizNumber;
		}

		public long getTeacherId() {
			return teacherId;
		}

		public void setTeacherId(long teacherId) {
			this.teacherId = teacherId;
		}

		public long getVersion() {
			return version;
		}

		public void setVersion(long version) {
			this.version = version;
		}






		public String getQuestions() {
			return questions;
		}






		public void setQuestions(String questions) {
			this.questions = questions;
		}
		
		
		
	 	

}
