/**
 * 
 */
package co.com.sercappital.dto;

import java.util.List;

/**
 * Objeto usado para la respuestas en los servicios REST de la consulta
 * del listado de subtemas
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/10/05
 *
 */
public class RespuestaSubTemasDTO extends BaseRespuestaDTO {
	
	private List<SubTemaDTO> listaSubTemas;

	/**
	 * @return the listaSubTemas
	 */
	public List<SubTemaDTO> getListaSubTemas() {
		return listaSubTemas;
	}

	/**
	 * @param listaSubTemas the listaSubTemas to set
	 */
	public void setListaSubTemas(List<SubTemaDTO> listaSubTemas) {
		this.listaSubTemas = listaSubTemas;
	}

}
