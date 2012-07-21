package zw.co.mitech.mtutor.util;

import java.util.HashMap;
import java.util.Map;

public class OptionsUtil {

	public static final String EQUALS = ":";
	public static final String SEPERATOR = ",";

	public static String convertAttributesMapToString(
			Map<String, String> attributesMap) {
		StringBuffer sb = new StringBuffer();
		for (String key : attributesMap.keySet()) {
			sb.append(key + EQUALS + attributesMap.get(key) + SEPERATOR);
		}
		return sb.toString();
	}

	public static Map<String, String> getOptionsMap(String msg) {

		Map<String, String> map = new HashMap<String, String>();
		String[] options = msg.split(",");
		for (String option : options) {
			String[] pair = option.split(":");
			if (pair.length > 1) {
				map.put(pair[0], pair[1]);
			}
		}

		return map;

	}

	public static void main(String[] args) {
		Map<String, String> newMap = new HashMap<String, String>();
		newMap.put("1", "Doug");
		newMap.put("2", "Stan");

		String response = convertAttributesMapToString(newMap);
		System.out.println(response);
		Map<String, String> map2 = getOptionsMap(response);
		for (String k : map2.keySet()) {
			System.out.println("Key:" + k + "  Value:" + map2.get(k));
		}
	}

}
