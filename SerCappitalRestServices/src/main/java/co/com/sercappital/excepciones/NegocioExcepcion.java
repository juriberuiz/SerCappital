package co.com.sercappital.excepciones;

/**
 * Manejo de excepciones para logica de negocio
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 * 
 */
@SuppressWarnings("serial")
public class NegocioExcepcion extends BaseException{

	/**
	 * Constructor por defecto
	 * 
	 */	
	public NegocioExcepcion() {
		super();
	}
	
	/**
	 * Constructor de la clase
	 * 
	 * @param mensaje
	 *            Mensaje arrojado por la excepción
	 */	
	public NegocioExcepcion(String mensaje) {
		
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
	public NegocioExcepcion(String codigoError, String mensaje) {
		super(codigoError, mensaje);
	}
	
	/**
	 * Constructor de la clase
	 * 
	 * @param throwable
	 *            Objeto de la excepción
	 */	
	public NegocioExcepcion(Throwable throwable) {
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
	public NegocioExcepcion(String codigoError, Throwable throwable) {
		super(codigoError, throwable);
	}
	
}