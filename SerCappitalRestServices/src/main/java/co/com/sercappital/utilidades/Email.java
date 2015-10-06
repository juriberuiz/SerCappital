package co.com.sercappital.utilidades;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.propiedades.PropiedadesManager;

/**
 * Clase que contiene la logca para el envio de correos
 * 
 * @author Juan David Uribe Ruiz
 * ejemplo tomado de: http://panamahitek.com/javamail-enviar-correos-electronicos-desde-java/
 *
 */
public class Email {
	
	/**
	 * Metodo encargado de enviar un correo electronico.
	 * 
	 * @param destinatario
	 * @param asunto
	 * @param mensaje
	 */
	 public void SendMail(String destinatario, String asunto, String mensaje) {
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.MAIL_SMTP_AUTH));
	        props.put("mail.smtp.starttls.enable", PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.MAIL_SMTP_STARTTLS_ENABLE));
	        props.put("mail.smtp.host", PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.MAIL_SMTP_HOST));
	        props.put("mail.smtp.port", PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.MAIL_SMTP_PORT));

	        Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(
	                        		PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CORREO_USUARIO), 
	                        		PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CORREO_CLAVE));
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CORREO_USUARIO)));
	            message.setRecipients(Message.RecipientType.TO,
	                    InternetAddress.parse(destinatario));
	            message.setSubject(asunto);
	            message.setText(mensaje);

	            Transport.send(message);
	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
}
