package co.com.sercappital.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.codec.binary.Base64;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.core.ObservacionesLogica;
import co.com.sercappital.core.SubTemasLogica;
import co.com.sercappital.core.TemasLogica;
import co.com.sercappital.core.UsuariosLogica;
import co.com.sercappital.dto.RespuestaListaObservacionesDTO;
import co.com.sercappital.dto.RespuestaLoginDTO;
import co.com.sercappital.dto.RespuestaObservacionDTO;
import co.com.sercappital.dto.RespuestaRegistroUsuarioDTO;
import co.com.sercappital.dto.RespuestaSubTemasDTO;
import co.com.sercappital.dto.RespuestaTemasDTO;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;
import co.com.sercappital.utilidades.UtilidadJson;

/**
 * Clase encargada de exponer los servicios rest
 * para la aplicacion de SerCappital.
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/27
 *
 */
@Path("/")
@Provider
public class ServiciosSerCappital {
	
	String charset;
	
	
	@GET
	@POST
	@Path("/login/{usuarioJson}")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response login(
			@PathParam("usuarioJson") String usuarioJson) throws Exception {
		Base64 base64 = new Base64();
		RespuestaLoginDTO respuestaLoginDTO;
		UsuariosLogica usuariosLogica;
		System.out.println("........login");
		Log.getInstance().info("[login] Llego (B64): " + usuarioJson, getClass());
		
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		
		usuariosLogica = new UsuariosLogica();
		respuestaLoginDTO = usuariosLogica.login(usuarioJson);
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaLoginDTO).getBytes(charset)), charset))
	            .build();
	}

	@GET
	@POST
	@Path("/registrarUsuario/{usuarioJson}")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response registrarUsuario(
			@PathParam("usuarioJson") String usuarioJson) throws Exception {
		Base64 base64 = new Base64();
		RespuestaRegistroUsuarioDTO respuestaRegistroUsuarioDTO;
		UsuariosLogica usuariosLogica;
		System.out.println("........registrarUsuario");
		Log.getInstance().info("[registrarUsuario] Llego (B64): " + usuarioJson, getClass());
		
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		
		usuariosLogica = new UsuariosLogica();
		respuestaRegistroUsuarioDTO = usuariosLogica.registrarUsuario(usuarioJson);
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaRegistroUsuarioDTO).getBytes(charset)), charset))
	            .build();
	}
	
	
	@GET
	@POST
	@Path("/modificarUsuario/{usuarioJson}")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response modificarUsuario(
			@PathParam("usuarioJson") String usuarioJson) throws Exception {
		Base64 base64 = new Base64();
		RespuestaRegistroUsuarioDTO respuestaRegistroUsuarioDTO;
		UsuariosLogica usuariosLogica;
		System.out.println("........modificarUsuario");
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		Log.getInstance().info("[modificarUsuario] Llego (B64): " + usuarioJson, getClass());
		
		
		usuariosLogica = new UsuariosLogica();
		respuestaRegistroUsuarioDTO = usuariosLogica.modificarUsuario(usuarioJson);
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaRegistroUsuarioDTO).getBytes(charset)), charset))
	            .build();
	}
	
	@GET
	@POST
	@Path("/consultaListaObservaciones/{usuarioJson}")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response consultaListaObservaciones(
			@PathParam("usuarioJson") String usuarioJson) throws Exception {
		Base64 base64 = new Base64();
		RespuestaListaObservacionesDTO respuestaListaObservacionesDTO;
		ObservacionesLogica observacionesLogica;
		System.out.println("........consultaListaObservaciones");
		Log.getInstance().info("[consultaListaObservaciones] Llego (B64): " + usuarioJson, getClass());
		
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		
		observacionesLogica = new ObservacionesLogica();
		respuestaListaObservacionesDTO = observacionesLogica.consultaListaObservaciones(usuarioJson);
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaListaObservacionesDTO).getBytes(charset)), charset))
	            .build();
	}
	
	@GET
	@POST
	@Path("/consultaDetalleObservacion/{observacionJson}")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response consultaDetalleObservacion(
			@PathParam("observacionJson") String observacionJson) throws Exception {
		Base64 base64 = new Base64();
		RespuestaObservacionDTO respuestaObservacionDTO;
		ObservacionesLogica observacionesLogica;
		System.out.println("........consultaDetalleObservacion");
		Log.getInstance().info("[consultaDetalleObservacion] Llego (B64): " + observacionJson, getClass());
		
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		
		observacionesLogica = new ObservacionesLogica();
		respuestaObservacionDTO = observacionesLogica.consultaDetalleObservacion(observacionJson);
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaObservacionDTO).getBytes(charset)), charset))
	            .build();
	}
	
	@GET
	@POST
	@Path("/eliminarObservacion/{observacionJson}")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response eliminarObservacion(
			@PathParam("observacionJson") String observacionJson) throws Exception {
		Base64 base64 = new Base64();
		RespuestaObservacionDTO respuestaObservacionDTO;
		ObservacionesLogica observacionesLogica;
		System.out.println("........eliminarObservacion");
		Log.getInstance().info("[eliminarObservacion] Llego (B64): " + observacionJson, getClass());
		
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		
		observacionesLogica = new ObservacionesLogica();
		respuestaObservacionDTO = observacionesLogica.eliminarObservacion(observacionJson);
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaObservacionDTO).getBytes(charset)), charset))
	            .build();
	}
	
	@GET
	@POST
	@Path("/registrarObservacion/{observacionJson}")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response registrarObservacion(
			@PathParam("observacionJson") String observacionJson) throws Exception {
		Base64 base64 = new Base64();
		RespuestaObservacionDTO respuestaObservacionDTO;
		ObservacionesLogica observacionesLogica;
		System.out.println("........registrarObservacion");
		Log.getInstance().info("[registrarObservacion] Llego (B64): " + observacionJson, getClass());
		
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		
		observacionesLogica = new ObservacionesLogica();
		respuestaObservacionDTO = observacionesLogica.registrarObservacion(observacionJson);
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaObservacionDTO).getBytes(charset)), charset))
	            .build();
	}
	
	@GET
	@POST
	@Path("/olvidoClave/{usuarioJson}")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response olvidoClave(
			@PathParam("usuarioJson") String usuarioJson) throws Exception {
		Base64 base64 = new Base64();
		RespuestaRegistroUsuarioDTO respuestaRegistroUsuarioDTO;
		UsuariosLogica usuariosLogica;
		System.out.println("........olvidoClave");
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		Log.getInstance().info("[olvidoClave] Llego (B64): " + usuarioJson, getClass());
		
		
		usuariosLogica = new UsuariosLogica();
		respuestaRegistroUsuarioDTO = usuariosLogica.olvidoClave(usuarioJson);
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaRegistroUsuarioDTO).getBytes(charset)), charset))
	            .build();
	}
	
	@GET
	@POST
	@Path("/agregarFotoObservacion/{observacionId}/{foto}")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response agregarFotoObservacion(
			@PathParam("observacionId") long observacionId,
			@PathParam("foto") String foto) throws Exception {
		Base64 base64 = new Base64();
		RespuestaObservacionDTO respuestaObservacionDTO;
		ObservacionesLogica observacionesLogica;
		System.out.println("........agregarFotoObservacion");
		Log.getInstance().info("[agregarFotoObservacion] Llego observacionId: " + observacionId + ", foto: " + foto, getClass());
		
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		
		observacionesLogica = new ObservacionesLogica();
		respuestaObservacionDTO = observacionesLogica.agregarFotoObservacion(observacionId, foto);
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaObservacionDTO).getBytes(charset)), charset))
	            .build();
	}
	
	
	@GET
	@POST
	@Path("/getAllTemas/")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response getAllTemas() throws Exception {
		Base64 base64 = new Base64();
		RespuestaTemasDTO respuestaTemasDTO;
		TemasLogica temasLogica;
		System.out.println("........getAllTemas");
		Log.getInstance().info("[getAllTemas] ", getClass());
		
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		
		temasLogica = new TemasLogica();
		respuestaTemasDTO = temasLogica.getAllTemas();
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaTemasDTO).getBytes(charset)), charset))
	            .build();
	}
	
	
	@GET
	@POST
	@Path("/getAllSubTemas/")
	@Produces(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@Consumes(ConstantesSerCappital.APPLICATION_TEXT_UTF8)
	@DefaultValue("")
	public Response getAllSubTemas() throws Exception {
		Base64 base64 = new Base64();
		RespuestaSubTemasDTO respuestaSubTemasDTO;
		SubTemasLogica subTemasLogica;
		System.out.println("........getAllSubTemas");
		Log.getInstance().info("[getAllSubTemas] ", getClass());
		
		charset = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.CHARSET_ENCODIG);
		
		subTemasLogica = new SubTemasLogica();
		respuestaSubTemasDTO = subTemasLogica.getAllSubTemas();
		return Response
	            .status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .entity(new String(base64.encode(UtilidadJson.dtoToJson(respuestaSubTemasDTO).getBytes(charset)), charset))
	            .build();
	}
	
	

}