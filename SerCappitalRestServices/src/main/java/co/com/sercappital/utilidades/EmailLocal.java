package co.com.sercappital.utilidades;

import java.util.Properties;

import javax.mail.Message;
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
 * ejemplo tomado de: http://codehero.co/java-desde-cero-correos-electronicos/
 *
 */
public class EmailLocal {
	
	/**
	 * Metodo encargado de enviar un correo electronico.
	 * 
	 * @param destinatario
	 * @param asunto
	 * @param mensaje
	 */
	 public static void SendMail(String destinatario, String asunto, String mensaje) {

	    // La dirección de la cuenta de envío (from)
//	    String de = "juriberuiz@yahoo.es";
//
//	    // El servidor (host). En este caso usamos localhost
//	    String host = "localhost";

	    // Obtenemos las propiedades del sistema
	    Properties propiedades = System.getProperties();

	    // Configuramos el servidor de correo
	    propiedades.put("mail.smtp.host", PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.MAIL_SMTP_HOST));
	    //propiedades.put("mail.smtp.port", PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.MAIL_SMTP_PORT));

	    // Obtenemos la sesión por defecto
	    Session sesion = Session.getDefaultInstance(propiedades);
        try {
        	 // Creamos un objeto mensaje tipo MimeMessage por defecto.
            MimeMessage cuerpoMensaje = new MimeMessage(sesion);

            // Asignamos el "de o from" al header del correo.
            cuerpoMensaje.setFrom(new InternetAddress(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CORREO_USUARIO)));

            // Asignamos el "para o to" al header del correo.
            cuerpoMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            // Asignamos el asunto
            cuerpoMensaje.setSubject(asunto);

            // Asignamos el contenido HTML, tan grande como nosotros queramos
            cuerpoMensaje.setContent(mensaje,"text/html" );

            // Enviamos el correo
            Transport.send(cuerpoMensaje);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	 
	 
}
