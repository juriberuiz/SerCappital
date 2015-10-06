/**
 * 
 */
package co.com.sercappital.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto usado para el transporte de datos de los temas
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/22
 *
 */
@XmlRootElement
public class TemaDTO {
	
	private Long temaId;
	
	private String nombre;
	
	private String descripcion;
	
	private Long estadoId;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TemaDTO [temaId:" + temaId + ", nombre:" + nombre
				+ ", descripcion:" + descripcion + ", estadoId:" + estadoId + "]";
	}

	/**
	 * @return the temaId
	 */
	public Long getTemaId() {
		return temaId;
	}

	/**
	 * @param temaId the temaId to set
	 */
	public void setTemaId(Long temaId) {
		this.temaId = temaId;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the estadoId
	 */
	public Long getEstadoId() {
		return estadoId;
	}

	/**
	 * @param estadoId the estadoId to set
	 */
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
	
	

}
