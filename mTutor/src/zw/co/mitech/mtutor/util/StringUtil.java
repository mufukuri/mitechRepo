package zw.co.mitech.mtutor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil implements Serializable {
	
	/**
	 * 
	 */
	
	public static final String[] keySet = {"0","1","2","3","4","5","6","7","8","9"};//,"A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z"};
	public static Map<String,String> numberNames;
	public static Map<String,String> numbers;
	public static Map<String,String> numberWordMap;
	public static Map<String,String> newStudentMap;
	public static final String[] numNames ={
		
		    "one",
		    "two",
		    "three",
		    "four",
		    "five",
		    "six",
		    "seven"
	};
	
	
	public static final String [] numberList ={
		"1","2","3","4","5","6","7"
	};
	
	

	private static final long serialVersionUID = 1L;
	public static final SimpleDateFormat DDMMYY = new SimpleDateFormat("dd-MM-yy");
	
	public static boolean isInteger(String value){
		try{
			Integer.parseInt(value);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public static boolean validateMobile(String mobileNumber) {
		try {
			String mobile=formatMobileNumber(mobileNumber);
			//System.out.println(">>>>>>>>>>>>>>>>>"+mobile);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	
	public static boolean validateMobileNumber(String mobileNumber) {
		try {
			String mobile=formatMobileNumber(mobileNumber);
		//	System.out.println(">>>>>>>>>>>>>>>>>"+mobile);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	public static void main(String [] args){
		System.out.println(getGradeOptions());
		
	}
	
	public static String formatMobileNumber(String mobileNumber) throws Exception {
		if (mobileNumber == null) {
			throw new Exception("Mobile number is NULL");
		}
	//	System.out.println(">>>>>>>>>>>>>>>>>>>1");

		mobileNumber = mobileNumber.trim().replace(" ", "");

		if (mobileNumber.startsWith("+263")) {
			mobileNumber = mobileNumber.substring(1);
		}
	//	System.out.println(">>>>>>>>>>>>>>>>>>>2");
		if (mobileNumber.startsWith("07")) {
			mobileNumber = "263" + mobileNumber.substring(1);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>3");
		if (mobileNumber.startsWith("2637")) {
			System.out.println(">>>>>>>>>>>>>>>>>>>4");
			if (mobileNumber.length() != 12) {
				System.out.println(">>>>>>>>>>>>>>>>>>>5");
				throw new Exception("Mobile number length must be 12: yours has " + mobileNumber.length());
			}
		} else {
			System.out.println(">>>>>>>>>>>>>>>>>>>6");
			throw new Exception("Mobile Number is invalid: does not contain 07 prefix.");
		}
		return mobileNumber;
	}

	public static boolean isValidResponseLetter(String letter) {
		// TODO Auto-generated method stub
		if(letter!=null){
		letter = letter.trim();
		}
		if(letter.length()==0 || letter.length()>1){
			return false;
		}else if(letter.length()==1){
			Character.isLetter(letter.charAt(0));
			return true;
		}
	
		return false;
	}

	public static boolean isEmpty(String sessionState) {
		if(sessionState== null){
			return true;
		}else if(sessionState!=null){
			String trimmed = sessionState.trim();
			if(trimmed.length()==0){
				return true;
			}
		}
		return false;
	}

	public static String formatMobile(String mobile) {
		
		mobile = mobile.substring(mobile.length()-9);
		return "0"+mobile;
		
	}
	
	
	public static String getCustomerStateValueByKey(Txt txt,String key){
		txt.deserialiseSession();
		String value = txt.getFromSession(key);
		
		return value;
	}
	
	
	public static boolean isValidEmail(String email) {
		
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		//Match the given string with the pattern
		Matcher m = p.matcher(email);
		//check whether match is found
		boolean matchFound = m.matches();
		if (matchFound){
			return true;
		}else {
			return false;
		}
		
	}

public static boolean isValidMobile(String mobile) {
	
	if( mobile== null || mobile.length() < 9 || mobile.length() > 13){
		return false;
	}
	mobile = mobile.substring(mobile.length()-9);
	if(mobile.startsWith("77")){
		return true;
	}
	return false;
}

public static boolean isValidGrade(String grade) {
	initalizeMaps();
	grade= grade.toLowerCase();
	
	
	boolean check1  = numberNames.containsValue(grade);
	boolean check2  = numbers.containsValue(grade);
	if( check1 || check2){
		return true;
	}
	return false;
}

public static  void initalizeMaps(){
	numberNames = new TreeMap<String, String>();
	for(String number :numNames ){
	
		numberNames.put(number, number);
	}
	
	numbers = new TreeMap<String, String>();
	for( String value : numberList){
		numbers.put(value, value);
	}
	numberWordMap = new TreeMap<String,String>();
	numberWordMap.put("one", "1");
	numberWordMap.put("two", "2");
	numberWordMap.put("three", "3");
	numberWordMap.put("four", "4");
	numberWordMap.put("five", "5");
	numberWordMap.put("six", "6");
	numberWordMap.put("seven", "7");
}
	
public static boolean isvalidGradeNumber(String  grade){
	initalizeMaps();
	
	boolean check = numbers.containsValue(grade);
	return false;
}



public static boolean isValidGradeWord(String grade){
	initalizeMaps();
	grade =grade.toLowerCase();
	boolean check = numberNames.containsValue(grade);
	
	return check;
}


public static String trimName(String name) {
	if(name!= null){
		int length = name.length();
		if(length <= 10){
			return name;
		}else{
			name = name.substring(0,10);
		}
		
	}
	return name;
}


public void initializeWordNumberMap(){
	numberWordMap = new TreeMap<String,String>();
	numberWordMap.put("one", "1");
	numberWordMap.put("two", "2");
	numberWordMap.put("three", "3");
	numberWordMap.put("four", "4");
	numberWordMap.put("five", "5");
	numberWordMap.put("six", "6");
	numberWordMap.put("seven", "7");
}

public static String getNumber(String grade){
	grade = grade.toLowerCase();
	String number =numberWordMap.get(grade);
	return number;
}

public static int generateRandomInt(long a, long b) {
	int result = (int) (Math.floor((Math.abs(a-b)+1) * Math.random()) + Math.min(a, b));
	return result;
}

public static long generateRandomlong(long a, long b) {
	long result = (long) (Math.floor((Math.abs(a-b)+1) * Math.random()) + Math.min(a, b));
	return result;
}

public static String toCamelCase(String string){
	if(string == null){
		return null;
	}
	string = string.trim();
	return string.substring(0, 1).toUpperCase()+ string.toLowerCase().substring(1);
}

public static String generateQuizCode() {
	int max = keySet.length;
	StringBuilder buffer = new StringBuilder();
	for(int i=1 ; i<=5 ; i++){
		int key = (int)((Math.random()*300)%max);
		buffer.append(keySet[key]);
	}
	return buffer.toString();
}

public static String getPercentage(long selection, long total){
	//System.out.println("selection"+selection);
	//System.out.println("total"+total);
	double divisor =total;
	double percentage =0.0;
	if(selection== 0 || divisor ==0){
		
	}else{
	percentage =(selection/divisor);
	System.out.println("percentage"+percentage);
	}
	NumberFormat txt = new DecimalFormat("0%");
	
	return txt.format(percentage);
}


public static String convertStreamToString(InputStream is) throws IOException {
	/*
	 * To convert the InputStream to String we use the Reader.read(char[]
	 * buffer) method. We iterate until the Reader return -1 which means
	 * there's no more data to read. We use the StringWriter class to
	 * produce the string.
	 */
	if (is != null) {
		Writer writer = new StringWriter();

		char[] buffer = new char[1024];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} finally {
			is.close();
		}
		return writer.toString();
	} else {
		return "";
	}
}


public static String getTime(long milli){
	long seconds = milli/1000;
	
	long hours = seconds/3600;
	
	long remainder = seconds%3600;
	
	long minutes = remainder/60;
	
	remainder = remainder%60;
	return formatTime(hours) + ":" + formatTime(minutes) + ":" + formatTime(remainder);
}

private static String formatTime(long time) {
	if(time < 10){
		return "0"+time;
	}
	return ""+ time;
}

public static Map<String,String> getListofGrades() {
	//String gradeOptions
	Map<String,String>grades = new TreeMap<String,String>();
	grades.put("1", "Grade 1");
	grades.put("2", "Grade 2");
	grades.put("3", "Grade 3");
	grades.put("4", "Grade 4");
	grades.put("5", "Grade 5");
	grades.put("6", "Grade 6");
	grades.put("7", "Grade 7");

	
	
	return grades;
}


public static String getGradeOptions() {
	Map<String, String> grades=getListofGrades();
	StringBuilder msg = new StringBuilder();
	 Formatter formatter = new Formatter(msg, Locale.US);
	 
	 for(String grade : grades.keySet()){
		 formatter.format("%1$2s%2$-8s%n",grade+".", grades.get(grade));
	 }
	 return msg.toString();
}

public static String getStringValue(String string){
	return string == null ? "" : string;
}

public static long parseLong(String value) {
	if(value == null){
		return 0L;
	}
	 try{
		 return Long.parseLong(value);
	 }catch (Exception e) {
		return 0;
	}
}





}
