package zw.co.mitech.mtutor.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static SimpleDateFormat DDMMMYY= new SimpleDateFormat("dd-MMM-yyyy");
	
	public static Date getEndOfDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 99);
		return cal.getTime();
	}
	
	
	public static long daysBetween(Date start,Date end){
		long startMilSecs = start.getTime();
		long endMilSecs = end.getTime();
		long dif = Math.abs(startMilSecs - endMilSecs);
		long days = dif/(1000*60*60*24);
		return days;
	}
	
	
	public static void main(String...strings){
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 8, 16);
		Date start = cal.getTime();
		Date end = new Date();
		System.out.println("********* " + daysBetween(start, end));
	}

}
