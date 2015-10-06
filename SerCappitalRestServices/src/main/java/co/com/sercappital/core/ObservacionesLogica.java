package co.com.sercappital.core;

import java.util.List;

import org.apache.commons.codec.binary.Base64;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.constantes.ContantesMensajesRespuesta;
import co.com.sercappital.dataservice.broker.ObservacionDSBroker;
import co.com.sercappital.dto.ObservacionDTO;
import co.com.sercappital.dto.RespuestaListaObservacionesDTO;
import co.com.sercappital.dto.RespuestaObservacionDTO;
import co.com.sercappital.dto.UsuarioDTO;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;
import co.com.sercappital.utilidades.UtilidadJson;

/**
 * Clase con la logica para el procesamiento de las transacciones
 * referentes a las observaciones.
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/24
 *
 */
public class ObservacionesLogica {
	
	String charset;
	Base64 base64;
	
	public ObservacionesLogica() {
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		base64 = new Base64();
	}
	
	/**
	 * Metodo encargado de realizar la consulta de observaciones.
	 * 
	 * @param usuarioJson
	 * @return
	 */
	public RespuestaListaObservacionesDTO consultaListaObservaciones(String usuarioJson){
		UsuarioDTO usuarioDTO;
		RespuestaListaObservacionesDTO respuestaListaObservacionesDTO = new RespuestaListaObservacionesDTO();
		List<ObservacionDTO> listaObservaciones;
		
		try {
			usuarioJson = new String(base64.decode(usuarioJson.getBytes(charset)), charset);
			Log.getInstance().info("[consultaListaReporte] Llego: " + usuarioJson, getClass());
			
			usuarioDTO = (UsuarioDTO)UtilidadJson.jsonToDto(usuarioJson, UsuarioDTO.class);
			
			if(usuarioDTO.getUsuarioId() == null || usuarioDTO.getUsuarioId().equals(0L)){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_ID_USUARIO_OBSERVACIONES);
			}
			
			listaObservaciones = ObservacionDSBroker.getInstancia().getAllObservacionesByUsuarioId(usuarioDTO.getUsuarioId());
			
			respuestaListaObservacionesDTO.setListaObservaciones(listaObservaciones);
			respuestaListaObservacionesDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaListaObservacionesDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] consultaListaReporte: " + e.getMessage(), getClass());
			respuestaListaObservacionesDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaListaObservacionesDTO.setMensajeRespuesta(e.getMessage());
			respuestaListaObservacionesDTO.setListaObservaciones(null);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] consultaListaReporte: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaListaObservacionesDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaListaObservacionesDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_LISTA_OBSERVACIONES);
			respuestaListaObservacionesDTO.setListaObservaciones(null);
		}
		return respuestaListaObservacionesDTO;
	}
	
	/**
	 * Metodo encargado de consultar una observacion.
	 * 
	 * @param observacionJson
	 * @return
	 */
	public RespuestaObservacionDTO consultaDetalleObservacion(String observacionJson){
		ObservacionDTO observacionDTO, observacionDTOConsulta;
		RespuestaObservacionDTO respuestaObservacionDTO = new RespuestaObservacionDTO();
		
		try {
			observacionJson = new String(base64.decode(observacionJson.getBytes(charset)), charset);
			Log.getInstance().info("[consultaDetalleObservacion] Llego: " + observacionJson, getClass());
			
			observacionDTO = (ObservacionDTO)UtilidadJson.jsonToDto(observacionJson, ObservacionDTO.class);
			
			if(observacionDTO.getObservacionId() == null || observacionDTO.getObservacionId() == 0){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_ID_OBSERVACION);
			}
			
			observacionDTOConsulta = ObservacionDSBroker.getInstancia().getByIdObservaciones(observacionDTO.getObservacionId());
			
			if(observacionDTOConsulta == null || observacionDTOConsulta.getObservacionId() == null || observacionDTOConsulta.getObservacionId() == 0){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_OBSERVACION_NO_ENCONTRADA);
			}
			
			respuestaObservacionDTO.setObservacionDTO(observacionDTOConsulta);
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaObservacionDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] consultaDetalleObservacion: " + e.getMessage(), getClass());
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaObservacionDTO.setMensajeRespuesta(e.getMessage());
			respuestaObservacionDTO.setObservacionDTO(null);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] consultaDetalleObservacion: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaObservacionDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_OBSERVACION);
			respuestaObservacionDTO.setObservacionDTO(null);
		}
		return respuestaObservacionDTO;
	}
	
	/**
	 * Metodo encargado de eliminar una observacion.
	 * 
	 * @param observacionJson
	 * @return
	 */
	public RespuestaObservacionDTO eliminarObservacion(String observacionJson){
		ObservacionDTO observacionDTO, observacionConsultaDTO;
		RespuestaObservacionDTO respuestaObservacionDTO = new RespuestaObservacionDTO();
		
		try {
			observacionJson = new String(base64.decode(observacionJson.getBytes(charset)), charset);
			Log.getInstance().info("[eliminarObservacion] Llego: " + observacionJson, getClass());
			
			observacionDTO = (ObservacionDTO)UtilidadJson.jsonToDto(observacionJson, ObservacionDTO.class);
			
			if(observacionDTO.getObservacionId() == null || observacionDTO.getObservacionId().equals(0L)){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_ID_OBSERVACION_ELIMINAR);
			}
			
			/** Se consulta la observacion a eliminar */
			observacionConsultaDTO = ObservacionDSBroker.getInstancia().getByIdObservaciones(observacionDTO.getObservacionId());
			
			/** Se valida que la observacion se halla encontrado */
			if(observacionConsultaDTO == null || observacionConsultaDTO.getObservacionId() == null){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_OBSERVACION_ELIMINAR_NO_ENCONTRADA);
			}
			
			/** Se valida que la observacion no halla sido radicada */
			if(observacionConsultaDTO.getNumeroRadicado() != null && observacionConsultaDTO.getNumeroRadicado().trim().length() > 0){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_OBSERVACION_YA_RADICADA);
			}
				
			ObservacionDSBroker.getInstancia().deleteObservaciones(observacionDTO.getObservacionId());
			
			respuestaObservacionDTO.setObservacionDTO(null);
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaObservacionDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] eliminarObservacion: " + e.getMessage(), getClass());
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaObservacionDTO.setMensajeRespuesta(e.getMessage());
			respuestaObservacionDTO.setObservacionDTO(null);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] eliminarObservacion: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaObservacionDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_ELIMINAR_OBSERVACION);
			respuestaObservacionDTO.setObservacionDTO(null);
		}
		return respuestaObservacionDTO;
	}
	
	/**
	 * Metodo encargado de registrar una observacion en base de datos.
	 * 
	 * @param observacionJson
	 * @return
	 */
	public RespuestaObservacionDTO registrarObservacion(String observacionJson){
		ObservacionDTO observacionDTO;
		RespuestaObservacionDTO respuestaObservacionDTO = new RespuestaObservacionDTO();
		Long idObservacion;
		try {
			observacionJson = new String(base64.decode(observacionJson.getBytes(charset)), charset);
			Log.getInstance().info("[registrarObservacion] Llego: " + observacionJson, getClass());
			
			observacionDTO = (ObservacionDTO)UtilidadJson.jsonToDto(observacionJson, ObservacionDTO.class);
			
			if(observacionDTO.getLocalidadId() == null || observacionDTO.getLocalidadId().equals(0L)){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_OBSERVACION_LOCALIDAD);
			}
			
			if(observacionDTO.getTemaId() == null || observacionDTO.getTemaId().equals(0L)){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_OBSERVACION_TEMA);
			}
			
			if(observacionDTO.getSubTemaId() == null || observacionDTO.getSubTemaId().equals(0L)){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_OBSERVACION_SUBTEMA);
			}
			
			if(observacionDTO.getUsuarioId() == null || observacionDTO.getUsuarioId().equals(0L)){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_OBSERVACION_USUARIO);
			}
			
			if(observacionDTO.getDireccion() != null && observacionDTO.getDireccion().trim().length() > 0){
				observacionDTO.setDireccion(observacionDTO.getDireccion().replaceAll("#", "Nro."));
			}
			
			idObservacion = ObservacionDSBroker.getInstancia().insertObservaciones(
					observacionDTO.getLocalidadId(), 
					observacionDTO.getFoto(), 
					observacionDTO.getComentario(), 
					observacionDTO.getTemaId(), 
					observacionDTO.getDireccion(), 
					observacionDTO.getLatitud(), 
					observacionDTO.getSubTemaId(), 
					ConstantesSerCappital.ESTADO_ACTIVO, 
					observacionDTO.getUsuarioId(), 
					observacionDTO.getLongitud());
			
			observacionDTO = new ObservacionDTO();
			observacionDTO.setObservacionId(idObservacion);
			
			respuestaObservacionDTO.setObservacionDTO(observacionDTO);
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaObservacionDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] registrarObservacion: " + e.getMessage(), getClass());
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaObservacionDTO.setMensajeRespuesta(e.getMessage());
			respuestaObservacionDTO.setObservacionDTO(null);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] registrarObservacion: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaObservacionDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_INSERTAR_OBSERVACION);
			respuestaObservacionDTO.setObservacionDTO(null);
		}
		return respuestaObservacionDTO;
	}
	
	
	public RespuestaObservacionDTO agregarFotoObservacion(long observacionId, String foto){
//		ObservacionDTO observacionDTO;
		RespuestaObservacionDTO respuestaObservacionDTO = new RespuestaObservacionDTO();
		
		try {
//			observacionJson = new String(base64.decode(observacionJson.getBytes(charset)), charset);
//			Log.getInstance().info("[registrarObservacion] Llego: " + observacionJson, getClass());
//			
//			observacionDTO = (ObservacionDTO)UtilidadJson.jsonToDto(observacionJson, ObservacionDTO.class);
			
			if(foto == null || foto.trim().length() == 0){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_OBSERVACION_USUARIO);
			}
			
			ObservacionDSBroker.getInstancia().updateFotoObservaciones(observacionId, foto);
			
			respuestaObservacionDTO.setObservacionDTO(null);
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaObservacionDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] agregarFotoObservacion: " + e.getMessage(), getClass());
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaObservacionDTO.setMensajeRespuesta(e.getMessage());
			respuestaObservacionDTO.setObservacionDTO(null);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] agregarFotoObservacion: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaObservacionDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaObservacionDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_INSERTAR_OBSERVACION);
			respuestaObservacionDTO.setObservacionDTO(null);
		}
		return respuestaObservacionDTO;
	}
	
	
//	public static void main(String[] args) {
//		try {
//			ObservacionDTO observacionDTO = new ObservacionDTO();
//			observacionDTO.setComentario("comentario observacion fgsdfgsdfg");
////			observacionDTO.setObservacionId(1L);
//			observacionDTO.setDireccion("Calle 999 cra 34");
//			observacionDTO.setEstadoId(1L);
//			observacionDTO.setFoto("a");
//			observacionDTO.setLatitud("452365762");
//			observacionDTO.setLocalidadId(2L);
//			observacionDTO.setLongitud("67566345");
//			observacionDTO.setSubTemaId(2L);
//			observacionDTO.setTemaId(2L);
//			observacionDTO.setUsuarioId(1L);
////			String img = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAFA3PEY8MlBGQUZaVVBfeMiCeG5uePWvuZHI////////////////////////////////////////////////////2wBDAVVaWnhpeOuCguv/////////////////////////////////////////////////////////////////////////wAARCAGQASwDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwCvRRRTAWiiigApaKKQBRS0tACUooooGFFLRigAopcUUgEo5paKAE7UfWlxRQAlFLRQAlJS0daYCUUuKSgBOlGKWjFADaSnYpDQISkpaQ0wENFLSHjigBKKKKACiiigAooooAfRRRQAUtFFIApaKWgYUUUtABRS0UgDFFLRQAmKWiigAooooAMUUUUAJRS0UAJRS0lACdKKXFFACYpMUtFMBKSlNJQAlIaWkNAhppKU0lMAooooAXHFJSg+tKRmgBtGaKKAJMUU+jApDGUtLtoxQAUUUtABS0UtIBKWjFL9aAE6UtFFABRS0UAJRS0lABRRRQAlLRRQAlFFFACUUtFACUlLRQAhpKWkNMBDTT0pTTCeaBCGiilCkjNMAXGacVzTKcrdjQA2lB9acy9xTKAHEZptKp9adQBJS0UtSMKXFFLQAm0Um006loAZiipMUm0UANop200lABRRRQAUUUUAFFFFABRR3ooASiiigAooooASiiigBKKKDQAlITQaax755pgIzcdaZRQAScCmIKkQgjFDR8cdqjHBoAkdc8io6lVtw96a645FIBFbsaGXuKbTlbsaYDaUEihhjpSUAWKWilqRgKWgUtABS0UtABS0UUAFGM0tFADdtIVIp9FAEdFPIBpCvpQAyilINJQAUUUUAFJS0lABSUtJQAUUUUAJ1pDS00mgBCaiJyacxz0ptUIO9KpwadFjPPWldMcikBICCKjkXuKRTipeGFAyAHBqQEMKYw2nFIDg5FMQMuD7UlS8MKjIwaAFU9jSFTnikpQ2BQBYpaKWpGKKWkFLQAtLSUtABS0UUCCloooAKKKKAEopaKAEppAp1FAxhWm4qSigCKinlRTSpoASijFJQAUGikoAQ1G7dqc7YFRU0AU+NN3J6UzHepYm7GgQwqVPNSo24YNKy7hUXKmgYMNrYpVODTz86+9R0ASMNy1DUinFI69xQA0HBpxAYUylBxTENPFFSMNwyKjoAtUtJSipGKKWgUtACilpBS0AFLRRQIKWiigApKWigBKKWkoAKSlooASkpaKAEpKWigYhFNKinUUARlTTG4qamSLuUigCsTk0gBJwKUqV6ipYsfiaYiNeDg0rLtOR0qSRM801eRtNAx6NuHvQ655HWoxlGqYHIzSAhBwaVxkbhSyLg5pFOPpTAaKcD2PSmsNp9qKAEIwaKU8im0xCg4pxUHmm0ZNAFmlFJSipGKKUUlLQAtLSUtAC0UUtAgooooAKKKKACiiigBKKWkoAKSlooASiiigYlJS0UAJSGlooAYVzUZXaamppGRQAgORUbrg5pw+U04jIoAjI3L7ikRsGgZU0OO46GmBKQCKhIwcU+NuMGnOuR70gIxyMGmdDinUEbh70wCkIzSUtACUUGimIs0opKcKkYopaQUtAC0tJS0CCloooAKKKKACiiigAooooAKKKKAEooooAKSlooASkpaKBiUlLRQAlJS0UARuvehT2p+MjFRkYNACSL3po6YNS8MKiIwcUDG8g1KrZFMPIpFODQIV17im1L1FRMMGmAEZGR+NNpwOKRh3HSgApKKKYizThTacKkYopaQUtAC0tJS0CFooooAKWiigApKWigBKKWkoAKKKKACkpaSgAooooASilpKACkpaKBiUlLRQAlNcd6dRQBGpwaSReM0rDBpQcigZEKaRT2GDTetACo2KcwyKjp6nPFAiPpSg05xnkUymAhGDRS9Rg0nSmBapRTRThUgKKWkFKKAFpaKKBC0UUUALRRRQAUUUUAFFFFACUUUUAFFFFACUUtJQAUlLRQAlFFFAxKKWkoAKSlpKAGsMimA4NS1GwwaABxkVHUintTHGDQMaRSDinU0imIkByKjYYOe1AOKeeRQBHRx3FBGDiigCSF8jB6iphVNSVIIq2jBhkUMBwpRSClFIB1FFFAhaWkpaACiiigAooooAKKKKACkpaSgAooooAKSlooASiiigApKWigBKKKKBiUUUUAJSMMilooAipT8y0MMGkBwaBjKSnuO9MoASlU9qDTaYh7DIqOpAc0hXJoAiqeBuNvpUFORtrZpiLgpaaDkZp1SMdRRRQIWlpKKAFooooAKKKKACiiigApKWkoAKKKKACiiigApKWkoAKKKKAEopaSgYUlLSUAFJS0lACEZFRmpKaw5zQA0cjBph4OKd0NDDIyKBjKQilopiGg4p45phooAZRRRTEXI/uDHSnimR/cFPFSMUUtJS0CFooooAWiiigAooooAKKKKACkpaSgAooooAKKKKACkpaSgAooooAKSlpKACiiigYlJS0UAJSHkUtJQBGaQHtTmHemGgYjDBpKcfmHvTKYgNNp1JQAygDJAooHBpiLqjAxTqapyoPrThUjFpaSloELRRRQAtFFFABRRRQAUUUUAFJS0lABRRRQAUUUUAFFFFACUUUUAFFFFACUUUUDEopaSgBKSlpKAENRkVJTWFADOhprDBzTjSdRigY2ik6UtMRHRRTghKk0xE8DMUwMcetTCorddufcVLSYxaWkpaQhaKKKAFopKWgAooooAKKKKAEooooAKKKKACiiigAooooASiiigAooooASiiigYUlLSUAJSUtJQAhpKWkNADCMU2nsMimGgY1h3pKdTCMGmIbT4hk0ypYehpiJwuQDmnAEDmgdKWkMWlpKWkAtFJS0CCiiigBaKKKACkoooAKKKKACiiigAooooAKKKKAEooooAKKKKACkpaSgYUlLSUAJSUtIaAENIaU0lACUxhzT6aeRQAyjiikpgR1LGeKip6HApiRaBo3cgUwGnBqQySlptLSAWlpKWgQUUUUAFLSUUAFFFFABRRRQAUUUUAFFFFABRRSUAFFFFABRRRQAUlLSUDCkpaSgBKSlpKAEpDSmkoAbSUppKAGsKbTzTKYEdKDikopiJgacrciogacp+YfWkMtClpBS0gFooooELRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABSUUUAFFFFABRRRQAlFFFAwpKWkoASkpaSgBDSGlppoAQ0lKaQ0AJSEUtJTAhooopiFzT0++PrTBzUkSHcD2FAFoUtNFLUjHUUlLQIKWkpaACiiigAooooAKKKKACiikoAWkoooAKKKKACiiigAooooASiiigYUlFFACUlLSUAJTTTqaaAENJSmkoASkoopgMMTduaFiYnngVNS0XCxGYgCoWpk+6KY5IwQM09egzSAdS0lLQAtLSUUALRRRQIWikooAWikooAWikooAKKKKACiiigAooooAKKKKBhSUUUAFFFJQAUlLSUAFJRSUAJSUppKAENNpxppoASkpaSmBJS02lFIBT0pwptKKAHUtJS0ALS0lFAC0tJRQAtFFFAgooooAKKKKACiiigAooooAKKKSgYtJRRQAUUUUAFJRRQAUlFFACUlLSUAIaaaU0hoAQ0hpTTTQAU0kUjP6UynYRYpaSikMd2pV6U09DSr0oAdS0lLQAtLSUUAOopKKAFooooAWikooELRSUUDFopKKACiiigAooooAKKSigBaSiigApKKKACkopKACkopKACkoNIaAENRMxJxUh6UwjNNAMooIxRTET0tJS1IwPQ0q9KSnCgBaWkpaAFpabS0ALS0lFAC0tJRQAtFJRQAtFJRQAtFJRQAtFJRQAUUUUAFFFJQAtJRRQAUlFFABSUUlABSUUlABTTS000ANkJ4pm406TpTKoQuc0lFFAE1LSUopDFpabS0gHUtNpaAHUtNpaAFpaSigBaKKKAFopKKAFopKKAFopKKAFopKKACiiigAoopKAFpKKKACkoooAKSikoAKSikoASkNLTTTAa3IplPbpTKYgooooA//9k=";
////			Base64 base64 = new Base64();
//			String obser = UtilidadJson.dtoToJson(observacionDTO);
//			System.out.println(obser);
//			System.out.println(new String(new Base64().encode(obser.getBytes("UTF-8")), "UTF-8"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	

}
