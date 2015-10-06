package co.com.sercappital.utilidades;

import java.util.Random;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;

/**
 * Clase que contiene la logica para generar una contraeña aleatoria.
 * 
 * @author Juan David Uribe Ruiz
 *
 */
public class Claves {
	
	/**
	 * Metodo encargado de generar una nueva contraseña aleatoria.
	 * 
	 * @param longitud
	 * @return
	 */
	public static String generarClave(){
		 String cadenaAleatoria = "";
		 int longitud;
		 try {
			 longitud = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.LONGITUD_CLAVE));
		} catch (Exception e) {
			Log.getInstance().error("Error obteniendo el tamaño de la contraseña, " +
					"se va a poner 8 por defecto, error: " + e.getMessage(), Claves.class);
			longitud = 8;
		}
		 
		 long milis = new java.util.GregorianCalendar().getTimeInMillis();
		 Random r = new Random(milis);
		 int i = 0;
		 while ( i < longitud){
			 char c = (char)r.nextInt(255);
			 if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') || (c >='a' && c <='z') ){
				 cadenaAleatoria += c;
				 i ++;
			 }
		 }
		 return cadenaAleatoria;
	 }

}
