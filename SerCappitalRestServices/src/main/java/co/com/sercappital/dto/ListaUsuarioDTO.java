package co.com.sercappital.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto para transportar la lista de usuarios.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
@XmlRootElement
public class ListaUsuarioDTO {
	
	private List<UsuarioDTO> usuarioDTO;

	/**
	 * @return the usuarioDTO
	 */
	public List<UsuarioDTO> getUsuarioDTO() {
		return usuarioDTO;
	}

	/**
	 * @param usuarioDTO the usuarioDTO to set
	 */
	public void setUsuarioDTO(List<UsuarioDTO> usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}




}
