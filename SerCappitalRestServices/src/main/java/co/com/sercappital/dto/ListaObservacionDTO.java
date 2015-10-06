package co.com.sercappital.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto para transportar la lista de observaciones.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
@XmlRootElement
public class ListaObservacionDTO {
	
	private List<ObservacionDTO> observacionDTO;

	/**
	 * @return the observacionDTO
	 */
	public List<ObservacionDTO> getObservacionDTO() {
		return observacionDTO;
	}

	/**
	 * @param observacionDTO the observacionDTO to set
	 */
	public void setObservacionDTO(List<ObservacionDTO> observacionDTO) {
		this.observacionDTO = observacionDTO;
	}

	

}
