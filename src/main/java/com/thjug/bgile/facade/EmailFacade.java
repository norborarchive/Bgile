/**
 * Attribution CC BY This license lets others distribute, remix, tweak, and build upon your work, even commercially, as
 * long as they credit you for the original creation. This is the most accommodating of licenses offered. Recommended
 * for maximum dissemination and use of licensed materials.
 *
 * http://creativecommons.org/licenses/by/3.0/ http://creativecommons.org/licenses/by/3.0/legalcode
 */
package com.thjug.bgile.facade;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.thjug.bgile.interceptor.Logging;

/**
 * 
 * @author nuboat
 */
public class EmailFacade {

	@Logging
	public void send() {

		final String username = "username@gmail.com";
		final String password = "password";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("to-email@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
