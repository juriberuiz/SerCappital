package co.com.sercappital.utilidades;

/**
 * Clase que alacena la logica para encriptar datos 
 * en SHA512.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2016/09/24
 *
 */
public class CalcularSHA512 {
	
	public static final String SHA512 = "SHA-512";
	
	public static String calcularHash(String datoEncriptar) throws Exception {
		return Encriptador.encriptar(datoEncriptar, SHA512);
	}

}
