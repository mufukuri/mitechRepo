package zw.co.mitech.mtutor.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/*import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/

public class EmailService {
	
	public static final String FROM_EMAIL = "quiz@mtutor.co.zw";
	public static final String FROM_NAME = "Mobile Maths Tutor";
	

	public static void send(String fromEmail, String fromName, String to,String subject, String messageBody) {
		Properties props = new Properties();
		/*Session session = Session.getDefaultInstance(props, null);

		try {

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(fromEmail, fromName));

			message.addRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress(to));
			message.setSubject(subject);
			messageBody = messageBody.replace("\n", "<br/>");
			message.setContent(messageBody,"text/html");
			Transport.send(message);
		} catch (Exception e) {
			//System.out.println(">>>>>>>>>>"+e.getMessage());
			e.printStackTrace();
		}*/

	}
	
	public static String generateSubject(){
		SimpleDateFormat sf = new SimpleDateFormat("dd MMM yy HH:mm");
		return "Mtutor Response " + sf.format(new Date());
	}

	public static void sendEmail(String to, String msg, String subject) {
		// TODO Auto-generated method stub
		
	}

}
