package co.com.sercappital.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto para transportar la lista de subtemas.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
@XmlRootElement
public class ListaSubTemaDTO {
	
	private List<SubTemaDTO> subTemaDTO;

	/**
	 * @return the subTemaDTO
	 */
	public List<SubTemaDTO> getSubTemaDTO() {
		return subTemaDTO;
	}

	/**
	 * @param subTemaDTO the subTemaDTO to set
	 */
	public void setSubTemaDTO(List<SubTemaDTO> subTemaDTO) {
		this.subTemaDTO = subTemaDTO;
	}

	

	

}
