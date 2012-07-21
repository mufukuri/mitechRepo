package zw.co.mitech.mtutor.service;

import org.springframework.stereotype.Service;

import zw.co.mitech.mtutor.util.ApplicationConstants;
import zw.co.mitech.mtutor.util.HtmlMessagesUtil;
import zw.co.mitech.mtutor.util.MenuLevel;
import zw.co.mitech.mtutor.util.Messages;
import zw.co.mitech.mtutor.util.StringUtil;
import zw.co.mitech.mtutor.util.Txt;

@Service("notificationService")
public class NotificationService implements  SmsService,ChatService,EmailService {

	@Override
	public Txt processEmailRequest(Txt txt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Txt processChatRequest(Txt txt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Txt processSmsRequest(Txt txt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Txt shareWithFriends(Txt txt) {
		System.out.println("In share with friends");
		String txtMsg = txt.getTxtMsg();
			String[] contacts = txtMsg.split(",");
			if(contacts == null || contacts.length ==0){
				String msg = Messages.getSmsMsg(ApplicationConstants.INVALID_GENERAL_SHARE_HOME_KEY);
				//msg = StringUtil.insertUnreadMsgsCount(inboxMsgs, msg);
				txt.setMessage(msg);
				return txt;
			}
			
			boolean hasValidContacts = false;
			for (String contact : contacts) {
				if(StringUtil.isValidEmail(contact)){
					System.out.println("Valis email:"+contact);
					hasValidContacts = true;
					sendGeneralShareEmail(txt.getMobileNumber(),contact,txt);
				}else if(StringUtil.isValidMobile(contact)){
					hasValidContacts = true;
					sendGeneralShareSms(txt.getMobileNumber(),contact,txt);
				}
			}
			
			if(!hasValidContacts){
				String msg = Messages.getSmsMsg(ApplicationConstants.INVALID_GENERAL_SHARE_HOME_KEY);
				//msg = StringUtil.insertUnreadMsgsCount(inboxMsgs, msg);
				txt.setMessage(msg);
				return txt;
			}else{
				System.out.println("Done");
				String msg = Messages.getSmsMsg(ApplicationConstants.THANK_YOU_FOR_SHARING);
				//msg = StringUtil.insertUnreadMsgsCount(inboxMsgs, msg);
				txt.setMessage(msg);
				txt.clearSessionState();
				return txt;
			}
	
		
	}

	
	private void sendGeneralShareSms(String mobileNumber, String contact,
			Txt txt) {
		// TODO Auto-generated method stub
		/*
		 * for future implementation
		 */
		
	}

	private void sendGeneralShareEmail(String customer,String email,Txt txt) {
		System.out.println("sending general share email");
		String to = email;
		String subject = "Invitation to join mtutor";
		//FIXME construct proper msg here
		String msg = HtmlMessagesUtil.getInvitationMessage();
		zw.co.mitech.mtutor.util.EmailService.sendEmail(to, msg, subject);
		
	}
	
	public Txt gotoGeneralSharePage(Txt txt) {
		String msg = Messages.getSmsMsg(ApplicationConstants.GENERAL_SHARE_HOME_KEY);
		//msg = StringUtil.insertUnreadMsgsCount(inboxMsgs, msg);
		txt.setMessage(msg);
		txt.clearSessionState();
		txt.putInSession(ApplicationConstants.MENU_LEVEL, MenuLevel.INVITE_FRIENDS);
		return txt;
	}
	
	
	

}
