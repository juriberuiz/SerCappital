package co.com.sercappital.utilidades;

import java.security.MessageDigest;


/**
 * Clase que contien la logica para encriptar datos
 * en diferentes metodos de encripcion.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/34
 *
 */
public class Encriptador {
	
	/** Define la codificacion o charset a usar */
	public static final String CHARSET = "UTF-8";
	
	/**
	 * Metodo encargado de encriptar en diferentes tipos, 
	 * dependiento del tipo que diga la vairable type.
	 * 
	 * @param message
	 * @param type
	 * @return
	 */
	public static String encriptar(String message, String type) {
        MessageDigest md;
        byte[] buffer, digest;
        StringBuilder hash = new StringBuilder();
        try {
            buffer = message.getBytes(CHARSET);
            md = MessageDigest.getInstance(type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        md.update(buffer);
        digest = md.digest();
        for (byte b : digest) {
            hash.append(String.format("%02x", b & 0xff));
        }
        return hash.toString();
    }

}
