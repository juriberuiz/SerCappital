package co.com.sercappital.excepciones;

/**
 * Excepcion Base para la aplicacion
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 * 
 */
@SuppressWarnings("serial")
public class BaseException extends Exception{

	private String codigoError;
	
	/**
	 * Constructor por defecto
	 * 
	 */	
	public BaseException() {
		super();
	}

	/**
	 * Constructor de la clase
	 * 
	 * @param mensaje
	 *            Mensaje arrojado por la excepción
	 */	
	public BaseException(String mensaje) {
		
		super(mensaje);
	}
	
	/**
	 * Constructor de la clase
	 * 
	 * @param codigoError
	 *            Código del error que se genera
	 * @param mensaje
	 *            Mensaje arrojado por la excepción
	 */	
	public BaseException(String codigoError, String mensaje) {
		
		super(mensaje);
		this.setCodigoError(codigoError);		
	}
	
	/**
	 * Constructor de la clase
	 * 
	 * @param throwable
	 *            Objeto de la excepción
	 */	
	public BaseException(Throwable throwable) {
		super(throwable);
	}
	
	/**
	 * Constructor de la clase
	 * 
	 * @param codigoError
	 *            Código del error que se genera
	 * @param throwable
	 *            Objeto de la excepción
	 */	
	public BaseException(String codigoError, Throwable throwable) {
		super(codigoError, throwable);
		this.setCodigoError(codigoError);
	}

	/**
	 * Método encargado de retornar el valor del atributo codigoError
	 * 
	 * @return codigoError Valor del atributo
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * Método encargado de asignar el valor del atributo codigoError
	 * 
	 * @param codigoError
	 *            Valor a asignar al atributo codigoError
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	
}
