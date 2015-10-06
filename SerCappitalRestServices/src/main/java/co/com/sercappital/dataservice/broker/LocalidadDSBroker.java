package co.com.sercappital.dataservice.broker;

import java.util.LinkedList;
import java.util.List;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.dataservice.cliente.ClienteServiciosDataservice;
import co.com.sercappital.dto.ListaLocalidadDTO;
import co.com.sercappital.dto.LocalidadDTO;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.jaxb.parser.UtilidadesPojo;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;

/**
 * Broker con la logica para el consumo de los
 * dataservices de las Localidades.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
public class LocalidadDSBroker {
	
	/** Instancia del dataservice broker */
	private static LocalidadDSBroker instancia;
	
	/**
	 * Constructor
	 */
	private LocalidadDSBroker(){
		
	}

	/**
	 * M�todo encargado de obtener una instancia de la clase LocalidadDSBroker
	 * 
	 * @return LocalidadDSBroker Instancia de la clase
	 */
	public static LocalidadDSBroker getInstancia() {
		if (instancia == null) {
			instancia = new LocalidadDSBroker();
		}
		return instancia;
	}
	
	
	/**
	 * Metodo encargado de llamar el Dataservices de consulta de 
	 * todas las entidades
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<LocalidadDTO> getAllLocalidades() throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ListaLocalidadDTO listaLocalidadDTO = new ListaLocalidadDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_LOCALIDADES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_ALL_LOCALIDADES);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			listaLocalidadDTO = (ListaLocalidadDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								ListaLocalidadDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return listaLocalidadDTO.getLocalidadDTO();
	}
	
	/**
	 * Metodo encargado de consultar una localidad por Id.
	 * 
	 * @param localidadId
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LocalidadDTO getAllLocalidadesById(int localidadId) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		LocalidadDTO localidadDTO = new LocalidadDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_LOCALIDADES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_ALL_LOCALIDADES_ID);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(localidadId);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			localidadDTO = (LocalidadDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								LocalidadDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return localidadDTO;
	}
	
	/**
	 * Metodo encargado de eliminar una localidad de base de datos.
	 * 
	 * @param localidadId
	 * @throws NegocioExcepcion
	 */
	public void deleteLocalidad(int localidadId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_LOCALIDADES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_DELETE_LOCALIDAD);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(localidadId);
			
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
	 * Metdo encargado de insertar un registro en base de datos de una localidad.
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param numeroLocalidad
	 * @param estadoId
	 * @throws NegocioExcepcion
	 */
	public void insertLocalidad(String nombre, String descripcion, String numeroLocalidad, int estadoId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_LOCALIDADES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_INSERT_LOCALIDAD);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(nombre);
			listaParametros.add(descripcion);
			listaParametros.add(numeroLocalidad);
			listaParametros.add(estadoId);
			
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
	 * Metodo encargado de actualizar un registro de una localidad.
	 * 
	 * @param nombre
	 * @param localidadId
	 * @param descripcion
	 * @param numeroLocalidad
	 * @param estadoId
	 * @throws NegocioExcepcion
	 */
	public void updateLocalidad(String nombre, int localidadId, String descripcion, String numeroLocalidad, int estadoId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_LOCALIDADES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_UPDATE_LOCALIDAD);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(nombre);
			listaParametros.add(localidadId);
			listaParametros.add(descripcion);
			listaParametros.add(numeroLocalidad);
			listaParametros.add(estadoId);
			
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
