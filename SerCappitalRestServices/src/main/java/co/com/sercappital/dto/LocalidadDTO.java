package co.com.sercappital.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto usado para el transporte de datos de las localidades
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/22
 *
 */
@XmlRootElement
public class LocalidadDTO {
	
	private Long localidadId;
	
	private String nombre;
	
	private String numeroLocalidad;
	
	private String descripcion;
	
	private Long estadoId;

	

	@Override
	public String toString() {
		return "LocalidadDTO [localidadId:" + localidadId + ", nombre:"
				+ nombre + ", numeroLocalidad:" + numeroLocalidad
				+ ", descripcion:" + descripcion + ", estadoId:" + estadoId
				+ "]";
	}

	/**
	 * @return the localidadId
	 */
	public Long getLocalidadId() {
		return localidadId;
	}

	/**
	 * @param localidadId the localidadId to set
	 */
	public void setLocalidadId(Long localidadId) {
		this.localidadId = localidadId;
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
	 * @return the numeroLocalidad
	 */
	public String getNumeroLocalidad() {
		return numeroLocalidad;
	}

	/**
	 * @param numeroLocalidad the numeroLocalidad to set
	 */
	public void setNumeroLocalidad(String numeroLocalidad) {
		this.numeroLocalidad = numeroLocalidad;
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
