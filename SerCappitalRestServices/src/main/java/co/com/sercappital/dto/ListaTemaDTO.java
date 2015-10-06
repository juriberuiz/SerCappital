package co.com.sercappital.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto para transportar la lista de temas.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
@XmlRootElement
public class ListaTemaDTO {
	
	private List<TemaDTO> temaDTO;

	/**
	 * @return the temaDTO
	 */
	public List<TemaDTO> getTemaDTO() {
		return temaDTO;
	}

	/**
	 * @param temaDTO the temaDTO to set
	 */
	public void setTemaDTO(List<TemaDTO> temaDTO) {
		this.temaDTO = temaDTO;
	}



}
