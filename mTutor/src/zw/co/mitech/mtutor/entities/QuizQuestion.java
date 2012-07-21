package zw.co.mitech.mtutor.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

import zw.co.mitech.mtutor.util.StringUtil;

@Entity
@Table(name="QuizQuestion")
public class QuizQuestion  implements Serializable, Persistable {
	
	 	private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(length=160)
	    private String questionDetail;
	    private long questionNumber;
	    private long conceptNumber;
	    private String expectedAnswer;
	    private long academicLevelId;
	    private String detailedAnswer;
	    private long topicId;
	    private long conceptId;
	    private long subjectId;
	    private String optionA;
	    private String optionA_Response;
	    private String optionB;
	    private String optionB_Response;
	    private String optionC;
	    private String optionC_Response;
	    private String optionD;
	    private String optionD_Response;
	    //Easy,Medium,Hard	    
	    private String difficultyLevel;
	    @Version
	 	private long version;
	    
	    
	    @Temporal(javax.persistence.TemporalType.DATE)
	    private Date dateCreated;
	    private int points;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getQuestionDetail() {
			return questionDetail;
		}
		public void setQuestionDetail(String questionDetail) {
			this.questionDetail = questionDetail;
		}
		
		public long getQuestionNumber() {
			return questionNumber;
		}
		public void setQuestionNumber(long questionNumber) {
			this.questionNumber = questionNumber;
		}
		public String getExpectedAnswer() {
			return expectedAnswer;
		}
		public void setExpectedAnswer(String expectedAnswer) {
			this.expectedAnswer = expectedAnswer;
		}
		public long getAcademicLevelId() {
			return academicLevelId;
		}
		public void setAcademicLevelId(long academicLevelId) {
			this.academicLevelId = academicLevelId;
		}
		public String getDetailedAnswer() {
			return detailedAnswer;
		}
		public void setDetailedAnswer(String detailedAnswer) {
			this.detailedAnswer = detailedAnswer;
		}
		public long getTopicId() {
			return topicId;
		}
		public void setTopicId(long topicId) {
			this.topicId = topicId;
		}
		public Date getDateCreated() {
			return dateCreated;
		}
		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}
		public int getPoints() {
			return points;
		}
		public void setPoints(int points) {
			this.points = points;
		}
		@Override
		public void fetchParent(EntityManager em) {
			// TODO Auto-generated method stub
			
		}
		public String getSmsMessage(long nextQstnNumber) {
			StringBuilder builder = new StringBuilder();
			builder.append(nextQstnNumber+". "+ questionDetail);
			if(!StringUtil.isEmpty(optionA)){
				builder.append("\nA)"+optionA);
			}
			if(!StringUtil.isEmpty(optionB)){
				builder.append("\nB)"+optionB);
			}
			if(!StringUtil.isEmpty(optionC)){
				builder.append("\nC)"+optionC);
			}
			if(!StringUtil.isEmpty(optionD)){
				builder.append("\nD)"+optionD);
			}
			builder.append(getPointsLabel());
			return builder.toString();
		}
		
		private String getPointsLabel() {
			if(questionDetail.length() <= 130){
				if(points == 1){
					return "\n[1 mark]\n";
				}else{
					return "\n["+ points+ " marks]\n";
				}
			}else{
				return "\n["+points + "]\n";
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
		public String getOptionA() {
			return optionA;
		}
		public void setOptionA(String optionA) {
			this.optionA = optionA;
		}
		public String getOptionA_Response() {
			return optionA_Response;
		}
		public void setOptionA_Response(String optionA_Response) {
			this.optionA_Response = optionA_Response;
		}
		public String getOptionB() {
			return optionB;
		}
		public void setOptionB(String optionB) {
			this.optionB = optionB;
		}
		public String getOptionB_Response() {
			return optionB_Response;
		}
		public void setOptionB_Response(String optionB_Response) {
			this.optionB_Response = optionB_Response;
		}
		public String getOptionC() {
			return optionC;
		}
		public void setOptionC(String optionC) {
			this.optionC = optionC;
		}
		public String getOptionC_Response() {
			return optionC_Response;
		}
		public void setOptionC_Response(String optionC_Response) {
			this.optionC_Response = optionC_Response;
		}
		public String getOptionD() {
			return optionD;
		}
		public void setOptionD(String optionD) {
			this.optionD = optionD;
		}
		public String getOptionD_Response() {
			return optionD_Response;
		}
		public void setOptionD_Response(String optionD_Response) {
			this.optionD_Response = optionD_Response;
		}
		public String getDifficultyLevel() {
			return difficultyLevel;
		}
		public void setDifficultyLevel(String difficultyLevel) {
			this.difficultyLevel = difficultyLevel;
		}
		public String getExplanation(String answer) {
			StringBuffer buffer = new StringBuffer();
			if(answer == null){
				buffer.append(StringUtil.getStringValue(detailedAnswer));
			}else if("A".equalsIgnoreCase(answer)){
				buffer.append(StringUtil.getStringValue(optionA_Response));
			}else if("B".equalsIgnoreCase(answer)){
				buffer.append(StringUtil.getStringValue(optionB_Response));
			}else if("C".equalsIgnoreCase(answer)){
				buffer.append(StringUtil.getStringValue(optionC_Response));
			}else if("D".equalsIgnoreCase(answer)){
				buffer.append(StringUtil.getStringValue(optionD_Response));
			}else{
				buffer.append(StringUtil.getStringValue(detailedAnswer));
			}
			String explanation = buffer.toString();
			if(!StringUtil.isEmpty(explanation)){
				explanation = "\n"+explanation;
			}else {
				if(!StringUtil.getStringValue(detailedAnswer).isEmpty()){
					explanation = "\n"+detailedAnswer;
				}
			}
			return explanation;
		}
		public long getConceptNumber() {
			return conceptNumber;
		}
		public void setConceptNumber(long conceptNumber) {
			this.conceptNumber = conceptNumber;
		}
		public long getVersion() {
			return version;
		}
		public void setVersion(long version) {
			this.version = version;
		}
		
		
	    
	    

}
