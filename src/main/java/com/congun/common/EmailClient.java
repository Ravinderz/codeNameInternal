package com.congun.common;


import java.util.Properties;
import org.apache.log4j.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailClient {   

private static final Logger LOGGER = Logger.getLogger(EmailClient.class);
	public boolean sendEmail(EmailModel eModel){
		boolean status = false;
		// Sender's email ID needs to be mentioned
		String from = eModel.getFrom();
		String subject=eModel.getSubject();
		String msg =eModel.getMsg();
		
		String footer = "";
		
			msg = msg+footer;
/*
		// Assuming you are sending email from localhost
		//String host = "smtp.gmail.com";
		//	String host = "";
		// Get system properties object
		Properties props = new Properties();
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
			 
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "true");
		 
		LOGGER.debug("prpoerties have set");

		// Get the default Session object.
		Session mailSession  = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new  PasswordAuthentication("nishant.vengala@gmail.com", "9581223340");
				 // return new  PasswordAuthentication("NV00334638@TechMahindra.com", "nV@00334638");
			}});
		LOGGER.debug("Session object acquired");
		
*/
			
			// Assuming you are sending email from localhost
						String host = "a2plcpnl0637.prod.iad2.secureserver.net";

						// Get system properties object
						 Properties props = new Properties();
						 props.put("mail.smtp.user", from);
						 props.put("mail.smtp.host", host);
						 props.put("mail.smtp.port", "587");
						 props.put("mail.smtp.starttls.enable","true");
						 props.put("mail.smtp.debug", "true");
						 props.put("mail.smtp.auth", "true");
						 //props.put("mail.smtp.socketFactory.port", "465");
						 //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
						 //props.put("mail.smtp.socketFactory.fallback", "true");
						 System.out.println("prpoerties have set");

					 
					 
					// Get the default Session object.
					 Session mailSession  = Session.getInstance(props, new javax.mail.Authenticator() {
						 protected PasswordAuthentication getPasswordAuthentication(){
					 	    return new  PasswordAuthentication("admin@congun.com", "America123");
					 	    }});
					 System.out.println("Session object acquired");
			
		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(mailSession);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			LOGGER.debug("Sender added");
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(eModel.getTo()));
			/*
			message.addRecipient(Message.RecipientType.CC,
					new InternetAddress(commonCC));*/
			if(eModel.getCcRM()!=null)
			message.addRecipient(Message.RecipientType.CC,
					new InternetAddress(eModel.getCcRM()));
			LOGGER.debug("Recepients added");
			// Set Subject: header field
			message.setSubject(subject);
			LOGGER.debug("subject set");
			// Now set the actual message
			message.setContent(msg, "text/html; charset=utf-8");
			LOGGER.debug("Message set");
			// Send message
			Transport.send(message);

			LOGGER.debug("Sent message successfully....");

		    System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
			LOGGER.debug("Error: unable to send message....");
		}

		return status;
	}

}




