package co.com.sercappital.core;

import java.util.List;

import org.apache.commons.codec.binary.Base64;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.constantes.ContantesMensajesRespuesta;
import co.com.sercappital.dataservice.broker.SubtemasDSBroker;
import co.com.sercappital.dataservice.broker.TemasDSBroker;
import co.com.sercappital.dataservice.broker.UsuariosDSBroker;
import co.com.sercappital.dto.RespuestaLoginDTO;
import co.com.sercappital.dto.RespuestaRegistroUsuarioDTO;
import co.com.sercappital.dto.SubTemaDTO;
import co.com.sercappital.dto.TemaDTO;
import co.com.sercappital.dto.UsuarioDTO;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;
import co.com.sercappital.utilidades.CalcularSHA512;
import co.com.sercappital.utilidades.Claves;
import co.com.sercappital.utilidades.Email;
import co.com.sercappital.utilidades.UtilidadJson;

/**
 * Clase con la logica para el procesamiento de las transacciones
 * referentes a los usuarios.
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/24
 *
 */
public class UsuariosLogica {
	
	String charset;
	Base64 base64;
	
	public UsuariosLogica() {
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		base64 = new Base64();
	}
	
	/**
	 * Metodo encargado de realizar el login de un usuario.
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	public RespuestaLoginDTO login(String usuarioJson){
		UsuarioDTO usuarioDTO;
		RespuestaLoginDTO respuestaLoginDTO = new RespuestaLoginDTO();
		UsuarioDTO usuarioConsultaDTO;
		List<TemaDTO> listaTemas;
		List<SubTemaDTO> listaSubTemas;
		
		try {
			usuarioJson = new String(base64.decode(usuarioJson.getBytes(charset)), charset);
			Log.getInstance().info("[login] Llego: " + usuarioJson, getClass());
			
			usuarioDTO = (UsuarioDTO)UtilidadJson.jsonToDto(usuarioJson, UsuarioDTO.class);
			
			if(usuarioDTO.getUsuario() == null || usuarioDTO.getUsuario().trim().length() == 0){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_USUARIO_LOGIN);
			}
			
			if(usuarioDTO.getClave() == null){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_CLAVE_LOGIN);
			}
			
			usuarioConsultaDTO = UsuariosDSBroker.getInstancia().getByUsuarioUsuarios(usuarioDTO.getUsuario());
			
			if(usuarioConsultaDTO == null || 
					usuarioConsultaDTO.getUsuarioId() == null || 
					usuarioConsultaDTO.getUsuarioId().equals(0L)){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_USUARIO_NO_EXISTE);
			}
			
			if(!usuarioDTO.getClave().equals(usuarioConsultaDTO.getClave())){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_CLAVE_INVALIDA);
			}
			
			respuestaLoginDTO.setUsuarioDTO(usuarioConsultaDTO);
			
			listaTemas = TemasDSBroker.getInstancia().getAllTemas();
			listaSubTemas = SubtemasDSBroker.getInstancia().getAllSubtemas();
			
			respuestaLoginDTO.setListaTemas(listaTemas);
			respuestaLoginDTO.setListaSubTemas(listaSubTemas);
			
			respuestaLoginDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaLoginDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] login: " + e.getMessage(), getClass());
			respuestaLoginDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaLoginDTO.setMensajeRespuesta(e.getMessage());
			respuestaLoginDTO.setListaTemas(null);
			respuestaLoginDTO.setListaSubTemas(null);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] login: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaLoginDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaLoginDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_LOGIN);
			respuestaLoginDTO.setListaTemas(null);
			respuestaLoginDTO.setListaSubTemas(null);
		}
		Log.getInstance().info(respuestaLoginDTO.getCodigoRespuesta(), getClass());
		Log.getInstance().info(respuestaLoginDTO.getMensajeRespuesta(), getClass());
		return respuestaLoginDTO;
	}
	
	/**
	 * Metodo encargadgo de registrar un usuario en base de datos.
	 * @param usuarioDTO
	 * @return
	 */
	public RespuestaRegistroUsuarioDTO registrarUsuario(String usuarioJson){
		UsuarioDTO usuarioDTO;
		RespuestaRegistroUsuarioDTO respuestaRegistroUsuarioDTO = new RespuestaRegistroUsuarioDTO();
		UsuarioDTO usuarioConsultaDTO;
		
		try {
			usuarioJson = new String(base64.decode(usuarioJson.getBytes(charset)), charset);
			Log.getInstance().info("[registrarUsuario] Llego: " + usuarioJson, getClass());
			
			usuarioDTO = (UsuarioDTO)UtilidadJson.jsonToDto(usuarioJson, UsuarioDTO.class);
			
			if(usuarioDTO.getUsuario() == null || usuarioDTO.getUsuario().trim().length() == 0){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_USUARIO);
			}
			
			if(usuarioDTO.getClave() == null){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_CLAVE);
			}
			
			if(usuarioDTO.getCorreo() == null){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_CORREO);
			}
			
			if(usuarioDTO.getNombre() == null){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_NOMBRE);
			}
			
			usuarioConsultaDTO = UsuariosDSBroker.getInstancia().getByUsuarioUsuarios(usuarioDTO.getUsuario());
			if(usuarioConsultaDTO != null && 
					usuarioConsultaDTO.getUsuarioId() != null){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_USUARIO_YA_REGISTRADO);
			}
			
			if(usuarioDTO.getDireccion() != null && usuarioDTO.getDireccion().trim().length() > 0){
				usuarioDTO.setDireccion(usuarioDTO.getDireccion().replaceAll("#", "Nro."));
			}
			
			UsuariosDSBroker.getInstancia().insertUsuarios(
					usuarioDTO.getClave(), 
					usuarioDTO.getDireccion(), 
					usuarioDTO.getNombre(), 
					usuarioDTO.getUsuario(), 
					usuarioDTO.getCorreo(), 
					ConstantesSerCappital.ESTADO_ACTIVO, 
					usuarioDTO.getNumeroIdentificacion(), 
					usuarioDTO.getTelefono());
			
			
			respuestaRegistroUsuarioDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaRegistroUsuarioDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] registrarUsuario: " + e.getMessage(), getClass());
			respuestaRegistroUsuarioDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaRegistroUsuarioDTO.setMensajeRespuesta(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] registrarUsuario: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaRegistroUsuarioDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaRegistroUsuarioDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_REGISTRO_USR);
		}
		
		return respuestaRegistroUsuarioDTO;
	}
	
	/**
	 * Metodo encargado de modificar la informacion de un usuario
	 * en base de datos.
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	public RespuestaRegistroUsuarioDTO modificarUsuario(String usuarioJson){
		UsuarioDTO usuarioDTO;
		RespuestaRegistroUsuarioDTO respuestaRegistroUsuarioDTO = new RespuestaRegistroUsuarioDTO();
		
		try {
			usuarioJson = new String(base64.decode(usuarioJson.getBytes(charset)), charset);
			Log.getInstance().info("[modificarUsuario] Llego: " + usuarioJson, getClass());
			
			usuarioDTO = (UsuarioDTO)UtilidadJson.jsonToDto(usuarioJson, UsuarioDTO.class);
			
			if(usuarioDTO.getUsuarioId() == null || usuarioDTO.getUsuarioId() == 0){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_ID_USUARIO);
			}
			
			if(usuarioDTO.getClave() == null){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_CLAVE);
			}
			
			if(usuarioDTO.getNombre() == null){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_NOMBRE);
			}
			
			if(usuarioDTO.getDireccion() != null && usuarioDTO.getDireccion().trim().length() > 0){
				usuarioDTO.setDireccion(usuarioDTO.getDireccion().replaceAll("#", "Nro."));
			}
			
			UsuariosDSBroker.getInstancia().updateUsuarios(
					usuarioDTO.getClave(), 
					usuarioDTO.getDireccion(), 
					usuarioDTO.getNombre(), 
					usuarioDTO.getUsuarioId(), 
					ConstantesSerCappital.ESTADO_ACTIVO, 
					usuarioDTO.getNumeroIdentificacion(), 
					usuarioDTO.getTelefono());
			
			respuestaRegistroUsuarioDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaRegistroUsuarioDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_TRANSACCION_EXITOSA);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] modificarUsuario: " + e.getMessage(), getClass());
			respuestaRegistroUsuarioDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaRegistroUsuarioDTO.setMensajeRespuesta(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] modificarUsuario: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaRegistroUsuarioDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaRegistroUsuarioDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_MODIFICAR_USUARIO);
		}
		
		return respuestaRegistroUsuarioDTO;
	}
	
	/**
	 * Metodo con la logica para el cambio de clave.
	 * 
	 * @param usuarioJson
	 * @return
	 */
	public RespuestaRegistroUsuarioDTO olvidoClave(String usuarioJson){
		UsuarioDTO usuarioDTO, usuarioConsultaDTO;
		RespuestaRegistroUsuarioDTO respuestaRegistroUsuarioDTO = new RespuestaRegistroUsuarioDTO();
		String nuevaClave, nuevaClaveSha512, asunto, mensaje;
		Email email;
		try {
			usuarioJson = new String(base64.decode(usuarioJson.getBytes(charset)), charset);
			Log.getInstance().info("[olvidoClave] Llego: " + usuarioJson, getClass());
			
			usuarioDTO = (UsuarioDTO)UtilidadJson.jsonToDto(usuarioJson, UsuarioDTO.class);
			
			if(usuarioDTO.getCorreo() == null || usuarioDTO.getCorreo().trim().length() == 0){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_ERROR_NO_CORREO);
			}
			
			usuarioConsultaDTO = UsuariosDSBroker.getInstancia().getByCorreoUsuarios(usuarioDTO.getCorreo());
			
			if(usuarioConsultaDTO == null || 
					usuarioConsultaDTO.getUsuarioId() == null || 
					usuarioConsultaDTO.getUsuarioId().equals(0L)){
				throw new NegocioExcepcion(ContantesMensajesRespuesta.MSJ_CORREO_NO_ENCONTRADO);
			}
			
			nuevaClave = Claves.generarClave();
			nuevaClaveSha512 = CalcularSHA512.calcularHash(nuevaClave);
			
			UsuariosDSBroker.getInstancia().updateClaveUsuarios(nuevaClaveSha512, usuarioConsultaDTO.getUsuarioId());
			
			email = new Email();
			asunto = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CORREO_CAMBIOCLAVE_ASUNTO);
			mensaje = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CORREO_CAMBIOCLAVE_MENSAJE);
			mensaje = mensaje.replaceAll("<<clave>>", nuevaClave);
			email.SendMail(usuarioDTO.getCorreo(), asunto, mensaje);
			
			respuestaRegistroUsuarioDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_RTA_EXITOSA);
			respuestaRegistroUsuarioDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_CAMBIO_CLAVE_EXITO);
		} catch (NegocioExcepcion e) {
			Log.getInstance().error("[NegocioExcepcion] olvidoClave: " + e.getMessage(), getClass());
			respuestaRegistroUsuarioDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaRegistroUsuarioDTO.setMensajeRespuesta(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error("[Exception] olvidoClave: ", getClass());
			Log.getInstance().error(e, getClass());
			respuestaRegistroUsuarioDTO.setCodigoRespuesta(ContantesMensajesRespuesta.CODIGO_ERROR_GENERAL);
			respuestaRegistroUsuarioDTO.setMensajeRespuesta(ContantesMensajesRespuesta.MSJ_ERROR_GENERAL_OLVIDO_CLAVE);
		}
		
		return respuestaRegistroUsuarioDTO;
	}
	

}
