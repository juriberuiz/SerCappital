package co.com.sercappital.dto;

/**
 * Objeto usado como base para la respuestas en los servicios REST
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/24
 *
 */
public class BaseRespuestaDTO {
	
	protected String codigoRespuesta;
	
	protected String mensajeRespuesta;

	/**
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * @return the mensajeRespuesta
	 */
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	/**
	 * @param mensajeRespuesta the mensajeRespuesta to set
	 */
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	

}
