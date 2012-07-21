package zw.co.mitech.mtutor.service;

import java.io.Serializable;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zw.co.mitech.mtutor.entities.Student;
import zw.co.mitech.mtutor.entities.StudentSubject;
import zw.co.mitech.mtutor.session.RankingsFacade;
import zw.co.mitech.mtutor.session.StudentFacade;
import zw.co.mitech.mtutor.session.StudentSubjectFacade;
import zw.co.mitech.mtutor.util.ApplicationConstants;
import zw.co.mitech.mtutor.util.MenuLevel;
import zw.co.mitech.mtutor.util.Rank;
import zw.co.mitech.mtutor.util.StringUtil;
import zw.co.mitech.mtutor.util.Txt;


@Service("rankingService")
public class RankingService implements Processor, Serializable  {

	/**
	 * 
	 */
	
	
	@Autowired
	private RankingsFacade rankingFacade;
	
	private static final long serialVersionUID = -8282285650372385492L;

	@Override
	public Txt processTxtRequest(Txt txt) {
		String firstToken =txt.getToken(0);
		if(StringUtil.isInteger(firstToken) && ApplicationConstants.REQUEST_STATE.equalsIgnoreCase(txt.getSessionState())){
		List<Rank> studentRanks = rankingFacade.getStudentRankingsByAcademicLevel(txt.getAcademicLevelId(),5);
		int count =1;
		StringBuilder msg = new StringBuilder();
		msg.append("Ranking "+"\t"+"Points");
		msg.append("\n");
			for(Rank student : studentRanks){
				msg.append(count+"."+student.getFirstName()+"\t"+student.getPoints());
				msg.append("\n");
				++count;
				}
		String testString =	getRankings(studentRanks);
		msg.append(ApplicationConstants.VIEW_HOME_PAGE);	
		//txt.setMessage(msg.toString());
		txt.setMessage(testString+ApplicationConstants.VIEW_HOME_PAGE);
		txt.setSessionState(ApplicationConstants.TO_HOME_PAGE);
				}	
		
		else{
			// integer non existant
			
		}
		
			return txt;
	}

	
	public String getRankings(List<Rank> studentRanks){
		int count =1;
		StringBuilder msg = new StringBuilder();
		 Formatter formatter = new Formatter(msg, Locale.US);
		// msg.append("Ranking "+"\t"+"Points");
		// formatter.format("%1$2s%2$-21s%3$3s%n","", "","Pts");
		 for(Rank studentRank : studentRanks){
			
			 String firstName =studentRank.getFirstName();
			 firstName=StringUtil.trimName(firstName);
			 firstName =StringUtil.toCamelCase(firstName);
			
			 formatter.format("%1$2s%2$-15s%3$3s%n",studentRank.getRank()+".", firstName, studentRank.getPoints()+" pts");
		 //formatter.format(format, args)
		 ++count;
		 }
		 //System.out.println("String  \n"+msg.toString());
		 return msg.toString();
	}


	public Txt getRankings(Txt txt) {
		System.out.println("Viewing rankings");
		//List<Student> students =	studentFacade.getStudentsByPoints();
		//System.out.println(">>>>>>>>>>>>>>>>>>Mobile>>>>>>>>>>"+txt.getMobileNumber());
		
		//Student student =studentFacade.findStudentByMobileNumber(mobileNumber);
	
		long grade = txt.getAcademicLevelId();
		//System.out.println("grade>>>>>>>>>>>>"+grade);
		List<Rank> studentRanks = rankingFacade.getStudentRankingsByAcademicLevel(grade,5);
		
		StringBuilder msg = new StringBuilder();
		
		String testString =	getRankings(studentRanks);
		msg.append(ApplicationConstants.VIEW_HOME_PAGE);	
		//txt.setMessage(msg.toString());
		txt.setMessage(testString+ApplicationConstants.VIEW_HOME_PAGE);
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.HOME);
		return txt;
	}
	
	
	
	
}
