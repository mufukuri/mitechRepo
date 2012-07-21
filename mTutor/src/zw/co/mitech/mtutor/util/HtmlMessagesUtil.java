package zw.co.mitech.mtutor.util;

import java.io.IOException;
import java.io.InputStream;

public class HtmlMessagesUtil {
	
	private static String emailMsg;
	private static String invitationMsg;
	
	public static String getEmailHelpMessage(){
		if(emailMsg == null){
			initEmailMsg();
		}
		return emailMsg;
	}
	
	
	public static void initEmailMsg(){
		InputStream stream = HtmlMessagesUtil.class.getResourceAsStream("helpemail.html");
		try {
			emailMsg = StringUtil.convertStreamToString(stream);
			emailMsg = emailMsg.replace("\n", "");
			emailMsg = emailMsg.replace("${email}", Messages.getEmailMsg(ApplicationConstants.EMAIL_ADDRESS));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getInvitationMessage(){
		if(invitationMsg == null){
			initInvitationMsg();
		}
		return invitationMsg;
	}


	private static void initInvitationMsg() {
		InputStream stream = HtmlMessagesUtil.class.getResourceAsStream("invitationemail.html");
		try {
			invitationMsg = StringUtil.convertStreamToString(stream);
			invitationMsg = invitationMsg.replace("\n", "");
			//invitationMsg = emailMsg.replace("${email}", Messages.getEmailMsg(ApplicationConstants.EMAIL_ADDRESS));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
