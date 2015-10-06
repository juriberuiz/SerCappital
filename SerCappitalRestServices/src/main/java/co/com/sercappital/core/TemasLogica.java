package co.com.sercappital.core;

import java.util.List;

import org.apache.commons.codec.binary.Base64;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.constantes.ContantesMensajesRespuesta;
import co.com.sercappital.dataservice.broker.TemasDSBroker;
import co.com.sercappital.dto.RespuestaTemasDTO;
import co.com.sercappital.dto.TemaDTO;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;

/**
 * Clase con la logica para el procesamiento de las transacciones
 * referentes a los temas
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/10/05
 *
 */
public class TemasLogica {
	
	String charset;
	Base64 base64;
	
	public TemasLogica() {
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		base64 = new Base64();
	}
	
	/**
	 * Metodo encargado de realizar la consulta de todo el
	 * listado de temas
	 * 
	 * @return
	 */
	public RespuestaTemasDTO getAllTemas(){
		RespuestaTemasDTO respuestaTemasDTO = new RespuestaTemasDTO();
		List<TemaDTO> listaTemas;
		
		try {
			Log.getInstance().info("TemasLogica -> getAllTemas ", getClass());
			listaTemas = TemasDSBroker.getInstancia().getAllTemas();
			respuestaTemasDTO.setListaTemas(listaTemas);
			respuestaTemasDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaTemasDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] getAllTemas: " + e.getMessage(), getClass());
			respuestaTemasDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaTemasDTO.setMensajeRespuesta(e.getMessage());
			respuestaTemasDTO.setListaTemas(null);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] getAllTemas: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaTemasDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaTemasDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_LOGIN);
			respuestaTemasDTO.setListaTemas(null);
		}
		Log.getInstance().info(respuestaTemasDTO.getCodigoRespuesta(), getClass());
		Log.getInstance().info(respuestaTemasDTO.getMensajeRespuesta(), getClass());
		return respuestaTemasDTO;
	}
	

}
