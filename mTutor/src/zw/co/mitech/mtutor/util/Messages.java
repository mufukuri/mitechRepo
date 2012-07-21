package zw.co.mitech.mtutor.util;

import java.io.Serializable;
import java.util.ResourceBundle;




public class Messages implements Serializable {
	
	
	/**
	 * 
	 */
	
	private static final String HOME_MENU_PLACEHOLDER = "${homemenu}";
	private static final String SHORT_CODE_PLACEHOLDER = "${shortcode}";
	private static final String TOPICS_HOME_PLACE_HOLDER ="${topics}";
	private static final String HOME_INFO_PLACE_HOLDER ="${homeinfo}";
	private static final String HOME_INFO="home.info.msg";
	private static final String HOME_MENU = "home.menu";
	private static final String EMAIL_PLACEHOLDER = "${email}";
	private static final String EMAIL_MENU = "email.address";
	private static String emailMenu;
	private static final long serialVersionUID = 1L;
	private static ResourceBundle rb = ResourceBundle.getBundle("zw.co.mitech.mtutor.util.messages");
	private static String homeMenu;
	private static String homeinfo;
	
	public static String get(String key){
		String msg = rb.getString(key);
		msg = msg.replace(HOME_MENU_PLACEHOLDER, getHomeMenu(MessageSource.SMS));
		msg = msg.replace(HOME_INFO_PLACE_HOLDER, getHomeInfo());
		msg = msg.replace(SHORT_CODE_PLACEHOLDER, getShortCode());
		
		return msg;
	}
	
	public static String getSmsMsg(String key){
		String msg = get(key);
		return msg;
	}
	
	
	
	public static String getHomeMenu(String sms) {
		if(homeMenu == null){
			homeMenu = rb.getString(HOME_MENU);
		}
		return homeMenu;
	}

	
	public static String getHomeInfo(){
		if(homeinfo == null){
			homeinfo = rb.getString(HOME_INFO);
		}
		return homeinfo;
	}
	
	private static String getShortCode() {
		return "30000";
	}
	public static String getEmailMsg(String key){
		String msg = rb.getString(key);
		msg = msg.replace(EMAIL_PLACEHOLDER, getEmailMenu(MessageSource.EMAIL));
		
		return msg;
	}
	


	public static String getEmailMenu(String source) {
		if(emailMenu == null){
			emailMenu = rb.getString(EMAIL_MENU);
		}
		return emailMenu;
	}


	

	
	
	
	
}
