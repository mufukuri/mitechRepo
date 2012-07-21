package zw.co.mitech.mtutor.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringEscapeUtils;







public class Txt implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String message;
	
	private String sessionState;
	
	private String customerState;
	
	private String txtMsg;
	private long questionNumber;
	private String mobileNumber;
	private String firstName;
	private long academicLevelId;
	private long studentId;
	private long topicId;
	private long subjectId;
	private String studentSelection;
	
	Map<String,String> sessionAttributesMap;
	Map<String,String> persistentAttributesMap;
	Map<String,String> studentSelectionMap;

	
	private String source;
	
	private String[] tokens;
	
	public Txt() {
		super();
	}
	
	public Txt(HttpServletRequest req){
		this.setId(req.getParameter("Id"));
		this.setCustomerState(req.getParameter("CustomerState"));
		this.setMobileNumber(this.getCustomerState());
		this.setSessionState(req.getParameter("SessionState"));
		if(this.id == null || this.id.isEmpty()){
			this.id = ""+ System.currentTimeMillis();
		}
		this.setNewMessage(req.getParameter("Message"));
		
	}
	
	/*private void tokeniseMsg() {
		if(this.message!=null){
			this.message =message.trim();
		}
		String[] splits = getMessage().split(ApplicationConstants.SEPERATOR);
		List<String> validTokens = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		for (String token : splits) {
			if(!(ApplicationConstants.APPLICATION_TAG.equalsIgnoreCase(token)|| ApplicationConstants.SEPERATOR.equalsIgnoreCase(token) || token.isEmpty())){
				System.out.println("Token "+token);
				validTokens.add(token);
				builder.append(token+ApplicationConstants.SEPERATOR);	
			}
			
		}
		if(builder.length() > 0){
			txtMsg =  builder.substring(0, (builder.length()-ApplicationConstants.SEPERATOR.length()));
		}else{
			txtMsg = "";
		}
		tokens = new String[0];
		tokens = validTokens.toArray(splits);
		
	}*/
	
	
	public void setNewMessage(String message) {
		if(message != null){
			
			this.message = message.toLowerCase().trim();
			if(!this.message.startsWith(ApplicationConstants.APPLICATION_TAG)){
				this.message = ApplicationConstants.APPLICATION_TAG + " " + this.message;
			}
		}
		tokeniseMsg();
		deserialiseSession();
	}
	
	public void tokeniseMsg() {
		txtMsg = removeApplicationTag();
		String[] splits = txtMsg.split(ApplicationConstants.SEPERATOR);
		List<String> validTokens = new ArrayList<String>();
		for (String token : splits) {
			token = token.trim();
			if(!(ApplicationConstants.APPLICATION_TAG.equals(token) || ApplicationConstants.SEPERATOR.equals(token) || token.isEmpty())){
				validTokens.add(token);
				
			}
		}
		
		tokens = new String[0];
		tokens = validTokens.toArray(splits);
		
	}
	
	
	
	public void tokeniseTxtMsg(){
		String[] splits = txtMsg.split(ApplicationConstants.SEPERATOR);
		List<String> validTokens = new ArrayList<String>();
		for (String token : splits) {
			if(!(ApplicationConstants.APPLICATION_TAG.equals(token) || ApplicationConstants.SEPERATOR.equals(token) || token.isEmpty())){
				validTokens.add(token.trim());
				
			}
			
		}
		
		tokens = new String[0];
		tokens = validTokens.toArray(splits);
	}
	
	private String removeApplicationTag() {
		if(message != null){
			int start =message.lastIndexOf(ApplicationConstants.APPLICATION_TAG);
			if(start >= 0){
				int end = ApplicationConstants.APPLICATION_TAG.length()+ start;
				return message.substring(end);
			}
		}
		return "";
	}
	public boolean isEmpty(){
		return getTxtMsg().isEmpty();
	}

	public String getId() {
		return id;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	
	
	public void setMessage(String message) {
		
		this.message = message;
	}
	public String getCustomerState() {
		return customerState;
	}
	
	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}
	public String getSessionState() {
		return sessionState;
	}
	
	@XmlElement(name="sessionstate")
	public void setSessionState(String sessionState) {
		this.sessionState = sessionState;
	}

	public String getTxtMsg() {
		return txtMsg;
	}

	public void setTxtMsg(String txtMsg) {
		this.txtMsg = txtMsg;
	}
	

	
	public String getResponseMsg(){
		StringBuilder builder = new StringBuilder();
		builder.append("<mt>" );
		builder.append("<id>"+ getId() + "</id>" );
		builder.append("<msg>"+ getMessage() + "</msg>" );
		builder.append("<sessionstate>"+ (sessionState == null ? "" : StringEscapeUtils.escapeXml(sessionState)) + "</sessionstate>");
		builder.append("<customerstate>"+ (customerState == null ? "" : StringEscapeUtils.escapeXml(customerState)) + "</customerstate>");
		builder.append("</mt>");
		return builder.toString();
		
	}
	
	
	
	public String[] getTokens() {
		return tokens;
	}
	
	public int getNumberOfTokens(){
		return tokens == null ? 0 : tokens.length;
	}
	
	public String getToken(int index){
		try{
			String token = tokens[index];
			return token;
		}catch (Exception e) {
			return null;
		}
	}

	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}

	public static void main(String ...args){
		Txt txt = new Txt();
		txt.setId("0772107231");
		//txt.setMessage("#REG*0772890497*Doug");
		txt.setNewMessage("reg*0772890497*Douglas*7");
		txt.setCustomerState("custom state");
		txt.setSessionState("yyyty");
		txt.tokeniseMsg();
		System.out.println("First token**********"+txt.getToken(0) +"***********");
		System.out.println("Second token**********"+txt.getToken(1) +"***********");
		System.out.println("Third token**********"+txt.getToken(2) +"***********");
	}

	
	
	
	public String appendCustomerData(String data){
		String currentData=this.customerState;
		
		if(currentData!=null && currentData.length()==0){
			this.customerState=data;
		}else{
		this.customerState=this.customerState+"*"+data;
		}
		
		
		return customerState;
	}
	
	public long getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(long questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		this.customerState = mobileNumber;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		putInSession(ApplicationConstants.STUDENT_NAME,firstName);
	}
	
	public void clearSessionState() {
		backupPersistentState();
		this.sessionState = null;
		this.sessionAttributesMap = null;
		restorePersistentState();
	}
	
	private void restorePersistentState() {
		if(persistentAttributesMap != null){
			sessionAttributesMap = new HashMap<String, String>(persistentAttributesMap);
		}
	}

	private void backupPersistentState() {
		if(sessionAttributesMap != null){
			persistentAttributesMap = new HashMap<String, String>();
			persistentAttributesMap.put(ApplicationConstants.STUDENT_NAME, sessionAttributesMap.get(ApplicationConstants.STUDENT_NAME));
			persistentAttributesMap.put(ApplicationConstants.ACADEMIC_LEVEL_ID, sessionAttributesMap.get(ApplicationConstants.ACADEMIC_LEVEL_ID));
			persistentAttributesMap.put(ApplicationConstants.STUDENT_ID, sessionAttributesMap.get(ApplicationConstants.STUDENT_ID));
			persistentAttributesMap.put(ApplicationConstants.QUESTION_NUMBER, sessionAttributesMap.get(ApplicationConstants.QUESTION_NUMBER));
			persistentAttributesMap.put(ApplicationConstants.TOPIC_ID, sessionAttributesMap.get(ApplicationConstants.TOPIC_ID));
			persistentAttributesMap.put(ApplicationConstants.SUBJECT_ID, sessionAttributesMap.get(ApplicationConstants.SUBJECT_ID));
			
		}else{
			persistentAttributesMap = null;
		}
		
	}

	public void putInSession(String key,String value){
		if(sessionAttributesMap == null){
			sessionAttributesMap= new HashMap<String, String>();
		}
		sessionAttributesMap.put(key, value);
	}
	
	
	
	public String getFromSession(String key){
		if(sessionAttributesMap == null){
			return null;
		}else{
			
			/*for(String v :sessionAttributesMap.keySet()){
				System.out.println("key:"+v+"value="+sessionAttributesMap.get(v));
			}*/
			
			return sessionAttributesMap.get(key);
		}
		
	}
	
	public int getIntFromSession(String key){
		if(sessionAttributesMap == null){
			return 0;
		}else{
			String value = sessionAttributesMap.get(key);
			try{
				return Integer.parseInt(value);
			}catch (Exception e) {
				return 0;
			}
		}
		
	}
	
	/*public String serialiseSession(){
		if(sessionAttributesMap != null){
			customerState = MapUtil.convertAttributesMapToString(sessionAttributesMap);
		}
		return customerState;
	}*/

	public void serialiseSession(){
		if(sessionAttributesMap != null){
			sessionState = MapUtil.convertAttributesMapToString(sessionAttributesMap);
		}
	}
	
	
	/*public void deserialiseSession(){
		if(!StringUtil.isEmpty(customerState)){
			sessionAttributesMap = MapUtil.convertAttributesStringToMap(customerState);
		}
	}*/


	public void deserialiseSession(){
		if(!StringUtil.isEmpty(sessionState)){
			sessionAttributesMap = MapUtil.convertAttributesStringToMap(sessionState);
			setFirstName(sessionAttributesMap.get(ApplicationConstants.STUDENT_NAME));
			setAcademicLevelId(StringUtil.parseLong(sessionAttributesMap.get(ApplicationConstants.ACADEMIC_LEVEL_ID)));
			setStudentId(StringUtil.parseLong(sessionAttributesMap.get(ApplicationConstants.STUDENT_ID)));
			setQuestionNumberString(sessionAttributesMap.get(ApplicationConstants.QUESTION_NUMBER));
			setTopicId(StringUtil.parseLong(sessionAttributesMap.get(ApplicationConstants.TOPIC_ID)));
			setSubjectId(StringUtil.parseLong(sessionAttributesMap.get(ApplicationConstants.SUBJECT_ID)));
		}
	}
	private void setQuestionNumberString(String num) {
		if(StringUtil.isEmpty(num)){
		  setQuestionNumber(0);	
		}else{
			try{
				setQuestionNumber(Long.parseLong(num));
				putInSession(ApplicationConstants.QUESTION_NUMBER,num);
			}catch(Exception e){
				
			}
		}
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public long getAcademicLevelId() {
		return academicLevelId;
	}

	public void setAcademicLevelId(long academicLevelId) {
		this.academicLevelId = academicLevelId;
		putInSession(ApplicationConstants.ACADEMIC_LEVEL_ID, Long.toString(academicLevelId));
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
		putInSession(ApplicationConstants.STUDENT_ID,Long.toString(studentId));
	}

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
		putInSession(ApplicationConstants.TOPIC_ID,Long.toString(topicId));
	}

	public String getStudentSelection() {
		return studentSelection;
	}

	public void setStudentSelection(String studentSelection) {
		this.studentSelection = studentSelection;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
		putInSession(ApplicationConstants.SUBJECT_ID,Long.toString(subjectId));
	}
	
	
}
