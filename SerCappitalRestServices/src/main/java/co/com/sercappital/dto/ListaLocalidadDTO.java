package co.com.sercappital.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto para transportar la lista de localidades.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
@XmlRootElement
public class ListaLocalidadDTO {
	
	private List<LocalidadDTO> localidadDTO;

	/**
	 * @return the localidadDTO
	 */
	public List<LocalidadDTO> getLocalidadDTO() {
		return localidadDTO;
	}

	/**
	 * @param localidadDTO the localidadDTO to set
	 */
	public void setLocalidadDTO(List<LocalidadDTO> localidadDTO) {
		this.localidadDTO = localidadDTO;
	}

}
