package co.com.sercappital.dataservice.broker;

import java.util.LinkedList;
import java.util.List;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.dataservice.cliente.ClienteServiciosDataservice;
import co.com.sercappital.dto.ListaUsuarioDTO;
import co.com.sercappital.dto.UsuarioDTO;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.jaxb.parser.UtilidadesPojo;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;

/**
 * Broker con la logica para el consumo de los
 * dataservices de los usuarios.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
public class UsuariosDSBroker {
	
	/** Instancia del dataservice broker */
	private static UsuariosDSBroker instancia;
	
	/**
	 * Constructor
	 */
	private UsuariosDSBroker(){
		
	}

	/**
	 * M�todo encargado de obtener una instancia de la clase UsuariosDSBroker
	 * 
	 * @return UsuariosDSBroker Instancia de la clase
	 */
	public static UsuariosDSBroker getInstancia() {
		if (instancia == null) {
			instancia = new UsuariosDSBroker();
		}
		return instancia;
	}
	
	
	/**
	 * Metodo encargado de llamar el Dataservices de consulta de 
	 * todos los usuarios
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<UsuarioDTO> getAllUsuarios() throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ListaUsuarioDTO listaUsuarioDTO = new ListaUsuarioDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_USUARIOS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_ALL_USUARIOS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			listaUsuarioDTO = (ListaUsuarioDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								ListaUsuarioDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return listaUsuarioDTO.getUsuarioDTO();
	}
	
	/**
	 * Metodo encargado de consultar un usuario por Id.
	 * 
	 * @param usuarioId
	 * @return
	 * @throws NegocioExcepcion
	 */
	public UsuarioDTO getByIdUsuarios(Long usuarioId) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_USUARIOS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_BY_ID_USUARIOS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(usuarioId);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			usuarioDTO = (UsuarioDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								UsuarioDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return usuarioDTO;
	}
	
	/**
	 * Metodo encargado de eliminar un usuario de base de datos.
	 * 
	 * @param usuarioId
	 * @throws NegocioExcepcion
	 */
	public void deleteUsuarios(int usuarioId) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_USUARIOS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_DELETE_USUARIOS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(usuarioId);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
	}
	
	/**
	 * Metdo encargado de insertar un registro en base de datos de un usuario.
	 * 
	 * @param clave
	 * @param direccion
	 * @param nombre
	 * @param usuario
	 * @param correo
	 * @param estadoId
	 * @param numeroIdentificacion
	 * @param telefono
	 * @throws NegocioExcepcion
	 */
	public void insertUsuarios(String clave, String direccion, String nombre, String usuario, 
			String correo, int estadoId, String numeroIdentificacion, String telefono) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_USUARIOS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_INSERT_USUARIOS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(clave);
			listaParametros.add(direccion);
			listaParametros.add(nombre);
			listaParametros.add(usuario);
			listaParametros.add(correo);
			listaParametros.add(estadoId);
			listaParametros.add(numeroIdentificacion);
			listaParametros.add(telefono);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
	}
	
	/**
	 * Metodo encargado de actualizar un registro de un usuario.
	 * 
	 * @param clave
	 * @param direccion
	 * @param nombre
	 * @param usuarioId
	 * @param estadoId
	 * @param numeroIdentificacion
	 * @param telefono
	 * @throws NegocioExcepcion
	 */
	public void updateUsuarios(String clave, String direccion, String nombre,
			Long usuarioId, int estadoId, String numeroIdentificacion, 
			String telefono) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_USUARIOS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_UPDATE_USUARIOS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(clave);
			listaParametros.add(direccion);
			listaParametros.add(nombre);
			listaParametros.add(usuarioId);
			listaParametros.add(estadoId);
			listaParametros.add(numeroIdentificacion);
			listaParametros.add(telefono);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
	}
	
	/**
	 * Metodo encargado de consultar un usuario a partir del
	 * nombre de usuario.
	 * 
	 * @param usuario
	 * @return
	 * @throws NegocioExcepcion
	 */
	public UsuarioDTO getByUsuarioUsuarios(String usuario) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_USUARIOS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_BY_USUARIO_USUARIOS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(usuario);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			usuarioDTO = (UsuarioDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								UsuarioDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return usuarioDTO;
	}
	
	/**
	 * Metodo encargado de consultar un usuario por correo
	 * @param correo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public UsuarioDTO getByCorreoUsuarios(String correo) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_USUARIOS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_BY_CORREO_USUARIOS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(correo);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			usuarioDTO = (UsuarioDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								UsuarioDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return usuarioDTO;
	}
	
	/**
	 * Metodo encargado de actualizar la clave de un usuario
	 * @param clave
	 * @param usuarioId
	 * @throws NegocioExcepcion
	 */
	public void updateClaveUsuarios(String clave, Long usuarioId) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_USUARIOS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_CLAVE_UPDATE_USUARIOS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(clave);
			listaParametros.add(usuarioId);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
	}
	

}
