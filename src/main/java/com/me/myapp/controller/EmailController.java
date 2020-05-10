package com.me.myapp.controller;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailController {

	public static boolean sendEmail(String from, String to, String msgBody, String title) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("theadda2020@gmail.com", "neu@2020"));
			email.setSSLOnConnect(true);
			email.setFrom("theadda2020@gmail.com"); // This user email does not
			email.setSubject(title);
			email.setMsg(msgBody); // Retrieve email from the DAO and send this
			email.addTo(to);
			email.send();
			return true;
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}
		return false;
	}
}