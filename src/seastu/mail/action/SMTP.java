package seastu.mail.action;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SMTP implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 Properties p = System.getProperties();
	        p.put("mail.smtp.starttls.enable", "true");
	        p.put("mail.smtp.host", "smtp.gmail.com");
	        p.put("mail.smtp.auth", "true");
	        p.put("mail.smtp.port", "587");
	 
	        Authenticator auth = new MyAuthentication();
	        Session session = Session.getDefaultInstance(p, auth);
	        MimeMessage msg = new MimeMessage(session);
	 
	        try {
	            msg.setSentDate(new Date());
	            InternetAddress from = new InternetAddress();
	            
	           String sender = request.getParameter("sender");
	           String subject = request.getParameter("subject");
	           String content = request.getParameter("content");
	            
	            from = new InternetAddress(sender+"@gmail.com");
	            msg.setFrom(from);
	 
	            InternetAddress to = new InternetAddress("iwishyouhappy50@gmail.com");
	            msg.setRecipient(Message.RecipientType.TO, to);
	 
	            msg.setSubject(subject, "UTF-8");
	            msg.setText(content, "UTF-8");
	            msg.setHeader("content-Type", "text/html");
	 
	            javax.mail.Transport.send(msg);
	            System.out.println("M : transport 성공");
	        } catch (AddressException addr_e){
	            addr_e.printStackTrace();
	    } catch (MessagingException msg_e){
         msg_e.printStackTrace();
     }
		
	    ActionForward forward = new ActionForward();
	    forward.setPath("./mail/sendComplete.jsp");
	    forward.setRedirect(false);
	        
		return forward;
	}
	
}
        class MyAuthentication extends Authenticator {
        	 
            PasswordAuthentication account;
         
            public MyAuthentication(){
                String id = "iwishyouhappy50";
                String pw = "irwyhkmlutmmykyh";
                account = new PasswordAuthentication(id, pw);
            }
         
            public PasswordAuthentication getPasswordAuthentication(){
                return account;
            }
        }
	        

