/**
 * 
 */
package co.com.sercappital.dto;

import java.util.List;

/**
 * Objeto usado para la respuestas en los servicios 
 * REST de la consulta de lista de observaciones
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/24
 *
 */
public class RespuestaListaObservacionesDTO extends BaseRespuestaDTO {
	
	private List<ObservacionDTO> listaObservaciones;

	/**
	 * @return the listaObservaciones
	 */
	public List<ObservacionDTO> getListaObservaciones() {
		return listaObservaciones;
	}

	/**
	 * @param listaObservaciones the listaObservaciones to set
	 */
	public void setListaObservaciones(List<ObservacionDTO> listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}
	
	

}
