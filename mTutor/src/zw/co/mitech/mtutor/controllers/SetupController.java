package zw.co.mitech.mtutor.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import zw.co.mitech.mtutor.entities.AcademicLevel;
import zw.co.mitech.mtutor.entities.Concept;
import zw.co.mitech.mtutor.entities.Quiz;
import zw.co.mitech.mtutor.entities.QuizQuestion;
import zw.co.mitech.mtutor.entities.SchoolClass;
import zw.co.mitech.mtutor.entities.Subject;
import zw.co.mitech.mtutor.entities.Topic;
import zw.co.mitech.mtutor.generators.CountingGenerator;
import zw.co.mitech.mtutor.session.AcademicLevelFacade;
import zw.co.mitech.mtutor.session.ConceptFacade;
import zw.co.mitech.mtutor.session.QuizFacade;
import zw.co.mitech.mtutor.session.QuizQuestionFacade;
import zw.co.mitech.mtutor.session.SchoolClassFacade;
import zw.co.mitech.mtutor.session.SubjectFacade;
import zw.co.mitech.mtutor.session.TopicFacade;
import zw.co.mitech.mtutor.util.ClassType;
import zw.co.mitech.mtutor.util.GeneratedTopics;
import zw.co.mitech.mtutor.util.QuizType;
import zw.co.mitech.mtutor.util.StringUtil;

@Controller
@RequestMapping("/setup")
public class SetupController {
	
	@Autowired
	private SubjectFacade subjectFacade;
	
	@Autowired
	private AcademicLevelFacade academicLevelFacade;
	
	@Autowired
	private TopicFacade topicFacade;
	
	@Autowired
	private QuizQuestionFacade questionFacade;
	
	@Autowired
	private ConceptFacade conceptFacade;
	
	@Autowired
	private QuizFacade quizFacade;
	
	@Autowired
	private SchoolClassFacade schoolClassFacade;
	
	@RequestMapping("/page")
	public String gotoSetupPage(){
		return "setup";
	}
	
	@RequestMapping("/process")
	public String setupMtutor(){
		System.out.println("********* setting up mtutor *********");
		//create subject
		Subject maths = new Subject();
		maths.setName("Maths");
		subjectFacade.create(maths);
		
		  
		
		for(int i=1; i <= 7 ; i++){
			AcademicLevel academicLevel = new AcademicLevel();
			academicLevel.setDateCreated(new Date());
			academicLevel.setLevelName(""+i);
			academicLevelFacade.create(academicLevel);
			
			SchoolClass klass = new SchoolClass();
			klass.setAcademicLevelId(academicLevel.getId());
			klass.setClassName("All Grade " + i +"s");
			klass.setClassType(ClassType.GLOBAL);
			schoolClassFacade.create(klass);
			
		}
		
		AcademicLevel academicLevel = academicLevelFacade.findAcademicLevelByGrade("1");
		
		
		Topic counting = new Topic();
		counting.setAcademicLevelId(academicLevel.getId());
		counting.setDateCreated(new Date());
		counting.setTopicName(GeneratedTopics.Counting.toString());
		counting.setSubjectId(maths.getId());
		topicFacade.create(counting);
		
		Concept simpleCounting = new Concept();
		simpleCounting.setConceptName("Simple Counting");
		simpleCounting.setTopicId(counting.getId());
		simpleCounting.setTopicSequence(0);
		conceptFacade.create(simpleCounting);
		
		
		Concept complexCounting = new Concept();
		complexCounting.setConceptName("Complex Counting");
		complexCounting.setTopicId(counting.getId());
		complexCounting.setTopicSequence(1);
		conceptFacade.create(complexCounting);
		
		
		Topic addition = new Topic();
		addition.setAcademicLevelId(academicLevel.getId());
		addition.setDateCreated(new Date());
		addition.setTopicName(GeneratedTopics.Addition.toString());
		addition.setSubjectId(maths.getId());
		topicFacade.create(addition);
		
		
		Concept simpleAddition = new Concept();
		simpleAddition.setConceptName("Simple Addition");
		simpleAddition.setTopicId(addition.getId());
		simpleAddition.setTopicSequence(0);
		conceptFacade.create(simpleAddition);
		
		
		Concept complexAddition = new Concept();
		complexAddition.setConceptName("Complex Addition");
		complexAddition.setTopicId(addition.getId());
		complexAddition.setTopicSequence(1);
		conceptFacade.create(complexAddition);
		
		
		
		Topic subtraction = new Topic();
		subtraction.setAcademicLevelId(academicLevel.getId());
		subtraction.setDateCreated(new Date());
		subtraction.setTopicName(GeneratedTopics.Subtraction.toString());
		subtraction.setSubjectId(maths.getId());
		topicFacade.create(subtraction);
		
		
		Concept simpleSubtraction = new Concept();
		simpleSubtraction.setConceptName("Simple Subtraction");
		simpleSubtraction.setTopicId(subtraction.getId());
		simpleSubtraction.setTopicSequence(0);
		conceptFacade.create(simpleSubtraction);
		
		
		Concept complexSubtraction = new Concept();
		complexSubtraction.setConceptName("Complex Subtraction");
		complexSubtraction.setTopicId(subtraction.getId());
		complexSubtraction.setTopicSequence(1);
		conceptFacade.create(complexSubtraction);
		
		
		
		Topic comparing = new Topic();
		comparing.setAcademicLevelId(academicLevel.getId());
		comparing.setDateCreated(new Date());
		comparing.setTopicName(GeneratedTopics.Comparing.toString());
		comparing.setSubjectId(maths.getId());
		topicFacade.create(comparing);
		
		
		Concept simpleComparing = new Concept();
		simpleComparing.setConceptName("Simple Comparing");
		simpleComparing.setTopicId(comparing.getId());
		simpleComparing.setTopicSequence(0);
		conceptFacade.create(simpleComparing);
		
		
		Concept complexComparing = new Concept();
		complexComparing.setConceptName("Complex Comparing");
		complexComparing.setTopicId(comparing.getId());
		complexComparing.setTopicSequence(1);
		conceptFacade.create(complexComparing);
		
		
		List<Long> questionIds = new ArrayList<Long>();
		
		for(int i=1 ; i <=20 ; i++ ){
			
			QuizQuestion question = new QuizQuestion();
			question.setAcademicLevelId(academicLevel.getId());
			question.setDateCreated(new Date());
			question.setTopicId(counting.getId());
			question.setSubjectId(maths.getId());
			
			
			int points = 0;
			if(i <=10 ){
				
				question.setConceptId(simpleCounting.getId());
				points = 1;
			}else{
				question.setConceptId(complexCounting.getId());
				points = 2;
			}
			
			
			String token = getRandomToken();
			StringBuilder buffer = new StringBuilder("How many "+ token +" are there below ?\n\n");
			for(int j=1 ; j<= i ; j++){
				if(j <=40){
					buffer.append(token + " ");
					if(j%5 == 0){
						buffer.append("\n");
					}
				}else{
					buffer.append(token);
					if(j%10 == 0){
						buffer.append("\n");
					}
				}
			}
			buffer.append("\n");
			question.setQuestionDetail(buffer.toString());
			question.setPoints(points);
			question.setExpectedAnswer(""+ i);
			questionFacade.create(question);
			
			questionIds.add(question.getId());
			
			if(i%10 == 0){
				Quiz quiz = new Quiz();
				quiz.setConceptId(question.getConceptId());
				quiz.setQuestionIds(new  TreeSet<Long>(questionIds));
				quiz.setTopicId(counting.getId());
				quiz.setSubjectId(maths.getId());
				quiz.setQuizType(QuizType.CONCEPT);
				quiz.setCode(quizFacade.generateQuizCode());
				quizFacade.create(quiz);
				questionIds = new ArrayList<Long>();
			}
			
		}
		
		
		for(int i=1 ; i <=40 ; i++ ){
			
			QuizQuestion question = new QuizQuestion();
			question.setAcademicLevelId(academicLevel.getId());
			question.setDateCreated(new Date());
			question.setTopicId(addition.getId());
			question.setSubjectId(maths.getId());
			
			
			int points = 0;
			int a = 0;
			int b = 0;
			
			if(i<= 10){
				
				a = StringUtil.generateRandomInt(1, 5);
				b = StringUtil.generateRandomInt(1, 5);
			    points = 1;
			    question.setConceptId(simpleAddition.getId());
			}else if(i<=20){
				a = StringUtil.generateRandomInt(1, 5);
				b = StringUtil.generateRandomInt(5, 10);
			    points = 1;
			    question.setConceptId(simpleAddition.getId());
			}else if(i <=30){
				a = StringUtil.generateRandomInt(5, 10);
				b = StringUtil.generateRandomInt(5, 10);
				points = 2;
				 question.setConceptId(complexAddition.getId());
			}else{
				a = StringUtil.generateRandomInt(1, 5);
				b = StringUtil.generateRandomInt(10,15);
			    points = 2;
			    question.setConceptId(complexAddition.getId());
			}
			
			int sum = a+b;
			question.setQuestionDetail("What is\n"+a + " + " +  b + " ?" );
			question.setPoints(points);
			question.setExpectedAnswer(""+ sum);
			questionFacade.create(question);
			
			questionIds.add(question.getId());
			
			if(i%10 == 0){
				Quiz quiz = new Quiz();
				quiz.setConceptId(question.getConceptId());
				quiz.setQuestionIds(new  TreeSet<Long>(questionIds));
				quiz.setTopicId(addition.getId());
				quiz.setSubjectId(maths.getId());
				quiz.setQuizType(QuizType.CONCEPT);
				quiz.setCode(quizFacade.generateQuizCode());
				quizFacade.create(quiz);
				questionIds = new ArrayList<Long>();
			}
			
		}
		
		
		for(int i=1 ; i <=40 ; i++ ){
			
			QuizQuestion question = new QuizQuestion();
			question.setAcademicLevelId(academicLevel.getId());
			question.setDateCreated(new Date());
			question.setTopicId(subtraction.getId());
			question.setSubjectId(maths.getId());
			
			
			int points = 0;
			int a = 0;
			int b = 0;
			
			if(i<= 10){
				
				a = StringUtil.generateRandomInt(1, 5);
				b = StringUtil.generateRandomInt(1, 5);
			    points = 1;
			    question.setConceptId(simpleSubtraction.getId());
			}else if(i<=20){
				a = StringUtil.generateRandomInt(1, 5);
				b = StringUtil.generateRandomInt(5, 10);
			    points = 1;
			    question.setConceptId(simpleSubtraction.getId());
			}else if(i <=30){
				a = StringUtil.generateRandomInt(5, 10);
				b = StringUtil.generateRandomInt(5, 10);
				points = 2;
				 question.setConceptId(complexSubtraction.getId());
			}else{
				a = StringUtil.generateRandomInt(1, 5);
				b = StringUtil.generateRandomInt(10,15);
			    points = 2;
			    question.setConceptId(complexSubtraction.getId());
			}
			
			int minInt = Math.min(a, b);
			int maxInt = Math.max(a, b);
			int dif = maxInt -minInt;
			question.setQuestionDetail("What is  "+ maxInt +  "-" + minInt +" ?\n");
			question.setPoints(points);
			question.setExpectedAnswer(""+ dif);
			questionFacade.create(question);
			

			questionIds.add(question.getId());
			
			if(i%10 == 0){
				Quiz quiz = new Quiz();
				quiz.setConceptId(question.getConceptId());
				quiz.setQuestionIds(new  TreeSet<Long>(questionIds));
				quiz.setTopicId(addition.getId());
				quiz.setSubjectId(maths.getId());
				quiz.setQuizType(QuizType.CONCEPT);
				quiz.setCode(quizFacade.generateQuizCode());
				quizFacade.create(quiz);
				questionIds = new ArrayList<Long>();
			}
			
		}
		
		
		for(int i=1 ; i <=40 ; i++ ){
			
			QuizQuestion question = new QuizQuestion();
			question.setAcademicLevelId(academicLevel.getId());
			question.setDateCreated(new Date());
			question.setTopicId(comparing.getId());
			question.setSubjectId(maths.getId());
			
			
			int a =0;
			int b= 0;
			int points = 0;
			if(i<= 10){
				
				a = StringUtil.generateRandomInt(0, 5);
				b = StringUtil.generateRandomInt(0, 5);
			    points = 1;
			    question.setConceptId(simpleComparing.getId());
			}else if(i<=20){
				a = StringUtil.generateRandomInt(0, 5);
				b = StringUtil.generateRandomInt(5, 10);
			    points = 1;
			    question.setConceptId(simpleComparing.getId());
			}else if(i <=30){
				a = StringUtil.generateRandomInt(5, 10);
				b = StringUtil.generateRandomInt(10, 15);
				points = 2;
				question.setConceptId(complexComparing.getId());
			}else{
				a = StringUtil.generateRandomInt(0, 10);
				b = StringUtil.generateRandomInt(15,20);
			    points = 2;
			    question.setConceptId(complexComparing.getId());
			}
			
			
			StringBuilder buffer = new StringBuilder("Which words make this statement true?\n" + a +" ___ " + b + "\nA. is greater than\nB. is less than\nC. is equal to");
			if(a == b){
				question.setExpectedAnswer("C");
			}else  if(a>b){
				question.setExpectedAnswer("A");
			}else if(a<b){
				question.setExpectedAnswer("B");
			}
			
			question.setPoints(points);
			question.setQuestionDetail(buffer.toString());
			questionFacade.create(question);
			
			questionIds.add(question.getId());
			
			if(i%10 == 0){
				Quiz quiz = new Quiz();
				quiz.setConceptId(question.getConceptId());
				quiz.setQuestionIds(new  TreeSet<Long>(questionIds));
				quiz.setTopicId(addition.getId());
				quiz.setSubjectId(maths.getId());
				quiz.setQuizType(QuizType.CONCEPT);
				quiz.setCode(quizFacade.generateQuizCode());
				quizFacade.create(quiz);
				questionIds = new ArrayList<Long>();
			}
			
		}
		return "setup";
	}
	
	
	private String getRandomToken() {
		int max = CountingGenerator.tokens.length-1;
		int index = StringUtil.generateRandomInt(0,max);
		return CountingGenerator.tokens[index];
	}

}
