/*
 * Attribution
 * CC BY
 * This license lets others distribute, remix, tweak,
 * and build upon your work, even commercially,
 * as long as they credit you for the original creation.
 * This is the most accommodating of licenses offered.
 * Recommended for maximum dissemination and use of licensed materials.
 *
 * http://creativecommons.org/licenses/by/3.0/
 * http://creativecommons.org/licenses/by/3.0/legalcode
 */
package com.thjug.bgile.facade;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.PropertyService;
import java.util.Properties;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nuboat
 */
@Singleton
public class EmailFacade {

	private String from;
	private Session session;
	private Properties properties;

	@Inject
	private PropertyService service;

	@Transactional
	public boolean connect() {
		try {
			from = service.findById("mail.smtp.from").getString();

			properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.socketFactory.fallback", "false");
			properties.put("mail.smtp.host", service.findById("mail.smtp.host").getString());
			properties.put("mail.smtp.port", service.findById("mail.smtp.port").getString());
			//		properties.put("mail.smtp.debug", "true");
			//		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			//		properties.put("mail.smtp.socketFactory.fallback", "false");

			session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							service.findById("mail.smtp.username").getString(),
							service.findById("mail.smtp.password").getString());
				}
			});

			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	@Logging
	public void send(final String receiver, final String text, final String subject) throws MessagingException {
		final Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
		message.setSubject(subject);
		message.setText(text);

		Transport.send(message);
	}

}
