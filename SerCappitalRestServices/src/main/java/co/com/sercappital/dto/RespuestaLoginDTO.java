/**
 * 
 */
package co.com.sercappital.dto;

import java.util.List;

/**
 * Objeto usado para la respuestas en los servicios REST de Login
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/24
 *
 */
public class RespuestaLoginDTO extends BaseRespuestaDTO {
	
	private UsuarioDTO usuarioDTO;
	
	private List<TemaDTO> listaTemas;
	
	private List<SubTemaDTO> listaSubTemas;

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

	/**
	 * @return the usuarioDTO
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	/**
	 * @param usuarioDTO the usuarioDTO to set
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	
	

}
