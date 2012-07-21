package zw.co.mitech.mtutor.util;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class MapUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final String EQUALS ="==";
	public static final String SEPERATOR ="!!";
	
	public static String convertAttributesMapToString(Map<String,String> attributesMap){
		StringBuffer sb = new StringBuffer();
		for (String key : attributesMap.keySet()) {
			sb.append(key + EQUALS + attributesMap.get(key) + SEPERATOR );
		}
		return sb.toString();
	}
	
	
	public static String convertAttributesMapToString2(Map<String,String> attributesMap){
		StringBuffer sb = new StringBuffer();
		for (String key : attributesMap.keySet()) {
			sb.append(key + EQUALS + attributesMap.get(key) + SEPERATOR );
		}
		return sb.toString();
	}
	
	
	public static Map<String,String> convertAttributesStringToMap(String attributesString){
	
		if(attributesString == null){
			return null;
		}
		Map<String, String> attributesMap = new TreeMap<String, String>();
		int startIndex = 0;
		int endIndex =0;
		endIndex=attributesString.indexOf(EQUALS,startIndex);
		System.out.println("End index::::::"+endIndex);
		System.out.println("start index::::::"+startIndex);
		String key,value;
		while(endIndex >= 0){
			
			key = attributesString.substring(startIndex,endIndex);
			startIndex = endIndex + EQUALS.length();
			endIndex=attributesString.indexOf(SEPERATOR,startIndex);
			value =attributesString.substring(startIndex,endIndex);
			startIndex = endIndex + SEPERATOR.length();
			
			attributesMap.put(key, value);
			endIndex=attributesString.indexOf(EQUALS,startIndex);
		}
		return attributesMap;
	}
	
	
	public static void main(String[] args){
		Map<String, String> attributesMap = new TreeMap<String, String>();
		attributesMap.put("mobilenumber", "0772890497");
		attributesMap.put("questionNumber", "12");
		String str =convertAttributesMapToString(attributesMap);
		convertAttributesStringToMap(str);
		System.out.println("********* string " + str);
		
	}
	
	

}
