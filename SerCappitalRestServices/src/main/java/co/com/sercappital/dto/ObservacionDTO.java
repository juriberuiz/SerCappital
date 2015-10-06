/**
 * 
 */
package co.com.sercappital.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto usado para el transporte de datos de los para
 * el registro y consulta de observaciones.
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/22
 *
 */
@XmlRootElement
public class ObservacionDTO {
	
	private Long observacionId;
	
	private String direccion;
	
	private String comentario;
	
	private String longitud;
	
	private String latitud;
	
	private String foto;
	
	private String fechaReporte;// [String yyyy/MM/dd HH:mm:ss]
	
	private String fechaRadicado;// (null) [String yyyy/MM/dd HH:mm:ss]
	
	private String numeroRadicado;// (null) [String]
	
	private Long usuarioId;
	
	private Long temaId;
	
	private Long subTemaId;
	
	private Long localidadId;
	
	private Long estadoId;
	
	private String nombreTema;
	
	private String nombreSubTema;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ObservacionDTO [observacionId=" + observacionId
				+ ", direccion=" + direccion + ", comentario=" + comentario
				+ ", longitud=" + longitud + ", latitud=" + latitud + ", foto="
				+ foto + ", fechaReporte=" + fechaReporte + ", fechaRadicado="
				+ fechaRadicado + ", numeroRadicado=" + numeroRadicado
				+ ", usuarioId=" + usuarioId + ", temaId=" + temaId
				+ ", subTemaId=" + subTemaId + ", localidadId=" + localidadId
				+ ", estadoId=" + estadoId + ", nombreTema=" + nombreTema
				+ ", nombreSubTema=" + nombreSubTema + "]";
	}

	/**
	 * @return the observacionId
	 */
	public Long getObservacionId() {
		return observacionId;
	}

	/**
	 * @param observacionId the observacionId to set
	 */
	public void setObservacionId(Long observacionId) {
		this.observacionId = observacionId;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * @return the fechaReporte
	 */
	public String getFechaReporte() {
		return fechaReporte;
	}

	/**
	 * @param fechaReporte the fechaReporte to set
	 */
	public void setFechaReporte(String fechaReporte) {
		this.fechaReporte = fechaReporte;
	}

	/**
	 * @return the fechaRadicado
	 */
	public String getFechaRadicado() {
		return fechaRadicado;
	}

	/**
	 * @param fechaRadicado the fechaRadicado to set
	 */
	public void setFechaRadicado(String fechaRadicado) {
		this.fechaRadicado = fechaRadicado;
	}

	/**
	 * @return the numeroRadicado
	 */
	public String getNumeroRadicado() {
		return numeroRadicado;
	}

	/**
	 * @param numeroRadicado the numeroRadicado to set
	 */
	public void setNumeroRadicado(String numeroRadicado) {
		this.numeroRadicado = numeroRadicado;
	}

	/**
	 * @return the usuarioId
	 */
	public Long getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
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
	 * @return the subTemaId
	 */
	public Long getSubTemaId() {
		return subTemaId;
	}

	/**
	 * @param subTemaId the subTemaId to set
	 */
	public void setSubTemaId(Long subTemaId) {
		this.subTemaId = subTemaId;
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

	/**
	 * @return the nombreTema
	 */
	public String getNombreTema() {
		return nombreTema;
	}

	/**
	 * @param nombreTema the nombreTema to set
	 */
	public void setNombreTema(String nombreTema) {
		this.nombreTema = nombreTema;
	}

	/**
	 * @return the nombreSubTema
	 */
	public String getNombreSubTema() {
		return nombreSubTema;
	}

	/**
	 * @param nombreSubTema the nombreSubTema to set
	 */
	public void setNombreSubTema(String nombreSubTema) {
		this.nombreSubTema = nombreSubTema;
	}
	
	

}
