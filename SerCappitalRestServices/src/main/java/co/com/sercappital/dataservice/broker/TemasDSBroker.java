package co.com.sercappital.dataservice.broker;

import java.util.LinkedList;
import java.util.List;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.dataservice.cliente.ClienteServiciosDataservice;
import co.com.sercappital.dto.ListaTemaDTO;
import co.com.sercappital.dto.TemaDTO;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.jaxb.parser.UtilidadesPojo;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;

/**
 * Broker con la logica para el consumo de los
 * dataservices de los temas.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
public class TemasDSBroker {
	
	/** Instancia del dataservice broker */
	private static TemasDSBroker instancia;
	
	/**
	 * Constructor
	 */
	private TemasDSBroker(){
		
	}

	/**
	 * M�todo encargado de obtener una instancia de la clase TemasDSBroker
	 * 
	 * @return TemasDSBroker Instancia de la clase
	 */
	public static TemasDSBroker getInstancia() {
		if (instancia == null) {
			instancia = new TemasDSBroker();
		}
		return instancia;
	}
	
	
	/**
	 * Metodo encargado de llamar el Dataservices de consulta de 
	 * todas los temas
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<TemaDTO> getAllTemas() throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ListaTemaDTO listaTemaDTO = new ListaTemaDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_TEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_ALL_TEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			listaTemaDTO = (ListaTemaDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								ListaTemaDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return listaTemaDTO.getTemaDTO();
	}
	
	/**
	 * Metodo encargado de consultar un tema por Id.
	 * 
	 * @param temaId
	 * @return
	 * @throws NegocioExcepcion
	 */
	public TemaDTO getByIdTemas(int temaId) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		TemaDTO temaDTO = new TemaDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_TEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_BY_ID_TEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(temaId);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			temaDTO = (TemaDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								TemaDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return temaDTO;
	}
	
	/**
	 * Metodo encargado de eliminar un tema de base de datos.
	 * 
	 * @param temaId
	 * @throws NegocioExcepcion
	 */
	public void deleteTemas(int temaId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_TEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_DELETE_TEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(temaId);
			
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
	 * Metdo encargado de insertar un registro en base de datos de un tema.
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param estadoId
	 * @throws NegocioExcepcion
	 */
	public void insertTemas(String nombre, String descripcion, int estadoId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_TEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_INSERT_TEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(nombre);
			listaParametros.add(descripcion);
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
	 * Metodo encargado de actualizar un registro de un tema.
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param estadoId
	 * @throws NegocioExcepcion
	 * @param temaId
	 */
	public void updateTemas(String nombre, String descripcion, int estadoId, int temaId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_TEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_UPDATE_TEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(nombre);
			listaParametros.add(descripcion);
			listaParametros.add(estadoId);
			listaParametros.add(temaId);
			
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
