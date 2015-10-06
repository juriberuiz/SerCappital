package co.com.sercappital.dataservice.broker;

import java.util.LinkedList;
import java.util.List;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.dataservice.cliente.ClienteServiciosDataservice;
import co.com.sercappital.dto.GeneratedKeys;
import co.com.sercappital.dto.ListaObservacionDTO;
import co.com.sercappital.dto.ObservacionDTO;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.jaxb.parser.UtilidadesPojo;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;

/**
 * Broker con la logica para el consumo de los
 * dataservices de las observaciones.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
public class ObservacionDSBroker {
	
	/** Instancia del dataservice broker */
	private static ObservacionDSBroker instancia;
	
	/**
	 * Constructor
	 */
	private ObservacionDSBroker(){
		
	}

	/**
	 * M�todo encargado de obtener una instancia de la clase ObservacionDSBroker
	 * 
	 * @return ObservacionDSBroker Instancia de la clase
	 */
	public static ObservacionDSBroker getInstancia() {
		if (instancia == null) {
			instancia = new ObservacionDSBroker();
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
	public List<ObservacionDTO> getAllObservaciones() throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ListaObservacionDTO listaObservacionDTO = new ListaObservacionDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_OBSERVACIONES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_ALL_OBSERVACIONES);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			listaObservacionDTO = (ListaObservacionDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								ListaObservacionDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return listaObservacionDTO.getObservacionDTO();
	}
	
	/**
	 * Metodo encargado de consultar una observacion por Id.
	 * 
	 * @param observacionId
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ObservacionDTO getByIdObservaciones(Long observacionId) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ObservacionDTO observacionDTO = new ObservacionDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_OBSERVACIONES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_BY_ID_OBSERVACIONES);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(observacionId);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			observacionDTO = (ObservacionDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								ObservacionDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return observacionDTO;
	}
	
	/**
	 * Metodo encargado de eliminar una observacion de base de datos.
	 * 
	 * @param observacionId
	 * @throws NegocioExcepcion
	 */
	public void deleteObservaciones(Long observacionId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_OBSERVACIONES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_DELETE_OBSERVACIONES);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(observacionId);
			
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
	 * Metdo encargado de insertar un registro en base de datos de una observacion.
	 * 
	 * @param localidadId
	 * @param foto
	 * @param comentario
	 * @param temaId
	 * @param direccion
	 * @param latitud
	 * @param subTemaId
	 * @param estadoId
	 * @param usuarioId
	 * @param longitud
	 * @throws NegocioExcepcion
	 */
	public Long insertObservaciones(Long localidadId, String foto, String comentario, 
			Long temaId, String direccion, String latitud, Long subTemaId, 
			int estadoId, Long usuarioId, String longitud) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			Log.getInstance().info("Foto con B64: " + foto, getClass());
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_OBSERVACIONES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_INSERT_OBSERVACIONES);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(localidadId);
			listaParametros.add(foto);
			listaParametros.add(comentario);
			listaParametros.add(temaId);
			listaParametros.add(direccion);
			listaParametros.add(latitud);
			listaParametros.add(subTemaId);
			listaParametros.add(estadoId);
			listaParametros.add(usuarioId);
			listaParametros.add(longitud);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			GeneratedKeys generatedKeys = (GeneratedKeys)UtilidadesPojo.getXmlToPojo(
					respuestaServicio, GeneratedKeys.class);	
			
			return generatedKeys.getIdGenerado();
			
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
	}
	
	/**
	 * Metodo encargado de actualizar un registro de una observacion.
	 * 
	 * @param localidadId
	 * @param observacionId
	 * @param foto
	 * @param numeroRadicado
	 * @param comentario
	 * @param fechaReporte
	 * @param temaId
	 * @param direccion
	 * @param latitud
	 * @param subTemaId
	 * @param estadoId
	 * @param usuarioId
	 * @param longitud
	 * @param fechaRadicado
	 * @throws NegocioExcepcion
	 */
	public void updateObservaciones(int localidadId, int observacionId, String foto, 
			String numeroRadicado, String comentario, String fechaReporte, 
			int temaId, String direccion, String latitud, int subTemaId, 
			int estadoId, int usuarioId, String longitud, String fechaRadicado) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_OBSERVACIONES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_UPDATE_OBSERVACIONES);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			System.out.println("URL: " + URL + ", timeOut: " + timeout);
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(localidadId);
			listaParametros.add(observacionId);
			listaParametros.add(foto);
			listaParametros.add(numeroRadicado);
			listaParametros.add(comentario);
			listaParametros.add(fechaReporte);
			listaParametros.add(temaId);
			listaParametros.add(direccion);
			listaParametros.add(latitud);
			listaParametros.add(subTemaId);
			listaParametros.add(estadoId);
			listaParametros.add(usuarioId);
			listaParametros.add(longitud);
			listaParametros.add(fechaRadicado);
			
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
	 * 
	 * @param usuarioId
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ObservacionDTO> getAllObservacionesByUsuarioId(Long usuarioId) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ListaObservacionDTO listaObservacionDTO = new ListaObservacionDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_OBSERVACIONES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_ALL_OBSERVACIONES_BY_USUARIO_ID);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(usuarioId);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			listaObservacionDTO = (ListaObservacionDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								ListaObservacionDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return listaObservacionDTO.getObservacionDTO();
	}
	
	/**
	 * Metodo encargado de agregar un numero de radicado a una observacion.
	 * 
	 * @param observacionId
	 * @param numeroRadicado
	 * @throws NegocioExcepcion
	 */
	public void ponerRadicado(Long observacionId, Long numeroRadicado) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_OBSERVACIONES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_PONER_RADICADO);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(numeroRadicado);
			listaParametros.add(observacionId);
			
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
	 * Metodo encargado de agregar una foto a una observacion.
	 * 
	 * @param observacionId
	 * @param foto
	 * @throws NegocioExcepcion
	 */
	public void updateFotoObservaciones(Long observacionId, String foto) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_OBSERVACIONES);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_UPDATE_FOTO_OBSERVACIONES);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(foto);
			listaParametros.add(observacionId);
			
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
