package zw.co.mitech.mtutor.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.Student;
import zw.co.mitech.mtutor.entities.StudentTopic;
import zw.co.mitech.mtutor.session.StudentFacade;
import zw.co.mitech.mtutor.session.StudentTopicFacade;
import zw.co.mitech.mtutor.util.ApplicationConstants;
import zw.co.mitech.mtutor.util.Messages;
import zw.co.mitech.mtutor.util.StringUtil;
import zw.co.mitech.mtutor.util.Txt;

@Service("progressService")
public class StudentProgressService implements Processor, Serializable {

	@Autowired
	private StudentFacade studentFacade;
	@Autowired
	private StudentTopicFacade topicFacade;
	
	private static final long serialVersionUID = 1L;

	@Override
	public Txt processTxtRequest(Txt txt) {
		
		return txt;
	}

	public Txt viewStudentProgress(Txt txt) {
		
		long studentId =txt.getStudentId();
		
		List<StudentTopic> studentTopics= topicFacade.getStudentTopicsByStudentId(studentId,txt.getSubjectId());
		Student student = studentFacade.find(studentId);
		String msg =getProgress(studentTopics,student);
		
		txt.setMessage(msg+ApplicationConstants.VIEW_HOME_PAGE);
		return txt;
	}

	
	
	public  String getProgress(List<StudentTopic> studentTopics,Student student){
		
		StringBuilder msg = new StringBuilder();
		Formatter formatter = new Formatter(msg, Locale.US);
		
		 for(StudentTopic topic : studentTopics){
			 String topicName =topic.getTopic();
			 topicName =StringUtil.toCamelCase(topicName);
			 String percentage =StringUtil.getPercentage(topic.getPoints(), topic.getTotal()); 
			 formatter.format("%1$-15s%2$-4s%n",topicName,percentage);
			
		 }
		 String average= StringUtil.getPercentage(student.getPoints(), student.getTotal());
		 formatter.format("%1$-4s%2$-4s%n", "Pts:",""+student.getPoints()+"/"+student.getTotal());
		 formatter.format("%1$-4s%2$-4s%n%n", "Overall:",""+average);
		 System.out.println("String  \n"+msg.toString());
		 return msg.toString();
	}

	public static void main(String [] args){
		List<StudentTopic> studentTopics = new ArrayList<StudentTopic>();
		StudentTopic topic = new StudentTopic();
		topic.setTopic("Addition");
		topic.setCorrect(23);
		topic.setTotal(100);
		
		StudentTopic topic2 = new StudentTopic();
		topic2.setTopic("Subtraction");
		topic2.setCorrect(34);
		topic2.setTotal(100);
		
		StudentTopic topic3 = new StudentTopic();
		topic3.setTopic("Multiplication");
		topic3.setCorrect(12);
		topic3.setTotal(100);
		
		studentTopics.add(topic);
		studentTopics.add(topic3);
		studentTopics.add(topic2);
		Student student = new Student();
		student.setPoints(45);
		//getProgress(studentTopics, student);
	}
	
	
	
}
