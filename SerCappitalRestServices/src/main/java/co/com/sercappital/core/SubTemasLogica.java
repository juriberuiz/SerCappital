package co.com.sercappital.core;

import java.util.List;

import org.apache.commons.codec.binary.Base64;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.constantes.ContantesMensajesRespuesta;
import co.com.sercappital.dataservice.broker.SubtemasDSBroker;
import co.com.sercappital.dto.RespuestaSubTemasDTO;
import co.com.sercappital.dto.SubTemaDTO;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;

/**
 * Clase con la logica para el procesamiento de las transacciones
 * referentes a los subtemas
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/10/05
 *
 */
public class SubTemasLogica {
	
	String charset;
	Base64 base64;
	
	public SubTemasLogica() {
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		base64 = new Base64();
	}
	
	/**
	 * Metodo encargado de realizar la consulta de todo
	 * el listado de subtemas
	 * 
	 * @return
	 */
	public RespuestaSubTemasDTO getAllSubTemas(){
		RespuestaSubTemasDTO respuestaSubTemasDTO = new RespuestaSubTemasDTO();
		List<SubTemaDTO> listaSubTemas;
		
		try {
			Log.getInstance().info("SubTemasLogica -> getAllSubTemas ", getClass());
			listaSubTemas = SubtemasDSBroker.getInstancia().getAllSubtemas();
			respuestaSubTemasDTO.setListaSubTemas(listaSubTemas);
			respuestaSubTemasDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaSubTemasDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] getAllSubTemas: " + e.getMessage(), getClass());
			respuestaSubTemasDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaSubTemasDTO.setMensajeRespuesta(e.getMessage());
			respuestaSubTemasDTO.setListaSubTemas(null);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] getAllSubTemas: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaSubTemasDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaSubTemasDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_LOGIN);
			respuestaSubTemasDTO.setListaSubTemas(null);
		}
		Log.getInstance().info(respuestaSubTemasDTO.getCodigoRespuesta(), getClass());
		Log.getInstance().info(respuestaSubTemasDTO.getMensajeRespuesta(), getClass());
		return respuestaSubTemasDTO;
	}
	

}
