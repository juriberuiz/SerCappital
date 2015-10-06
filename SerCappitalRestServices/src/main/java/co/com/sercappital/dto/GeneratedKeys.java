package co.com.sercappital.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Pojo que contiene los datos que retornan los metodos que insertan algun 
 * registro y como resultado dan el identificador del registro que se acaba de
 * insertar.
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/29
 *
 */
@XmlRootElement(name="GeneratedKeys")
public class GeneratedKeys {
	
	private long idGenerado;

	/**
	 * @return the idGenerado
	 */
	public long getIdGenerado() {
		return idGenerado;
	}

	/**
	 * @param idGenerado the idGenerado to set
	 */
	public void setIdGenerado(long idGenerado) {
		this.idGenerado = idGenerado;
	}

}
