/**
 * 
 */
package co.com.sercappital.dto;


/**
 * Objeto usado para la respuestas en los servicios 
 * REST de la consulta de observaciones
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/24
 *
 */
public class RespuestaObservacionDTO extends BaseRespuestaDTO {
	
	private ObservacionDTO observacionDTO;

	/**
	 * @return the observacionDTO
	 */
	public ObservacionDTO getObservacionDTO() {
		return observacionDTO;
	}

	/**
	 * @param observacionDTO the observacionDTO to set
	 */
	public void setObservacionDTO(ObservacionDTO observacionDTO) {
		this.observacionDTO = observacionDTO;
	}

	
	

}
