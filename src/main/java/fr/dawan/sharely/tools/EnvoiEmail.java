package fr.dawan.sharely.tools;

import java.awt.font.TextMeasurer;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoiEmail {
	
	private static final String SERVEUR_SMTP = "localhost";
	private static final String UTILISATEUR_SMTP = "";
	private static final String MOTDEPASSE_SMTP = "";
	private static final String EXPEDITEUR_NOREPLY = "noreply@sharely.com";
	
	public static boolean envoyer(String titre, String contenu, String destinataire) {
		boolean avecErreur = false;
		
		Properties config = new Properties();
		config.setProperty("mail.transport.protocol", "smtp");
		config.setProperty("mail.smtp.host", SERVEUR_SMTP);
		config.setProperty("mail.smtp.user", UTILISATEUR_SMTP); /* TODO: utile ?? */
		config.setProperty("mail.from", EXPEDITEUR_NOREPLY);
	    Session session = Session.getDefaultInstance(config);
	    
	    MimeMessage message = new MimeMessage(session);
	    try {
	        message.setContent(contenu,"text/html");
	        message.setSubject(titre);
	        message.addRecipients(Message.RecipientType.TO, destinataire);
	        //message.addRecipients(Message.RecipientType.CC, copyDest);
	    } catch (MessagingException e) {
	    	avecErreur = true;
	        e.printStackTrace();
	    }
	    
	    if(!avecErreur) {
	    	Transport transport = null;
	        try {
	        	transport = session.getTransport("smtp");
				transport.connect(UTILISATEUR_SMTP, MOTDEPASSE_SMTP);
				Address[] adresses = {new InternetAddress(destinataire)};
				transport.sendMessage(message, adresses);
			} catch (MessagingException e) {
				avecErreur = true;
				e.printStackTrace();
			} 
            if (transport != null) {
                try {
					transport.close();
				} catch (MessagingException e) {
					avecErreur = true;
					e.printStackTrace();
				}
            }
	    }

		return !avecErreur;
	}

}
