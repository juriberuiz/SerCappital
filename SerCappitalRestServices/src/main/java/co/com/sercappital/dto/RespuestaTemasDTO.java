package co.com.sercappital.dto;

import java.util.List;

/**
 * Objeto usado para la respuestas en los servicios REST de la consulta
 * del listado de temas
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/10/05
 *
 */
public class RespuestaTemasDTO extends BaseRespuestaDTO {
	
	private List<TemaDTO> listaTemas;
	
	/**
	 * @return the listaTemas
	 */
	public List<TemaDTO> getListaTemas() {
		return listaTemas;
	}

	/**
	 * @param listaTemas the listaTemas to set
	 */
	public void setListaTemas(List<TemaDTO> listaTemas) {
		this.listaTemas = listaTemas;
	}


}
