package co.com.sercappital.dataservice.broker;

import java.util.LinkedList;
import java.util.List;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.dataservice.cliente.ClienteServiciosDataservice;
import co.com.sercappital.dto.ListaSubTemaDTO;
import co.com.sercappital.dto.SubTemaDTO;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.jaxb.parser.UtilidadesPojo;
import co.com.sercappital.propiedades.PropiedadesManager;
import co.com.sercappital.utilidad.logger.Log;

/**
 * Broker con la logica para el consumo de los
 * dataservices de los subtemas.
 * 
 * @author Juan David Uribe Ruiz
 * @date 2015/09/23
 *
 */
public class SubtemasDSBroker {
	
	/** Instancia del dataservice broker */
	private static SubtemasDSBroker instancia;
	
	/**
	 * Constructor
	 */
	private SubtemasDSBroker(){
		
	}

	/**
	 * M�todo encargado de obtener una instancia de la clase SubtemasDSBroker
	 * 
	 * @return SubtemasDSBroker Instancia de la clase
	 */
	public static SubtemasDSBroker getInstancia() {
		if (instancia == null) {
			instancia = new SubtemasDSBroker();
		}
		return instancia;
	}
	
	
	/**
	 * Metodo encargado de llamar el Dataservices de consulta de 
	 * todas los subtemas
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<SubTemaDTO> getAllSubtemas() throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		ListaSubTemaDTO listaSubTemaDTO = new ListaSubTemaDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_SUBTEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_ALL_SUBTEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			listaSubTemaDTO = (ListaSubTemaDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								ListaSubTemaDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return listaSubTemaDTO.getSubTemaDTO();
	}
	
	/**
	 * Metodo encargado de consultar un subtema por Id.
	 * 
	 * @param subTemaId
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SubTemaDTO getByIdSubtemas(int subTemaId) throws NegocioExcepcion{

		String URL = "";
		String nombreServicio;
		String dominioDataServices;
		String dominioServicio;
		String respuestaServicio;
		Integer timeout;
		LinkedList<Object> listaParametros = null;
		SubTemaDTO subTemaDTO = new SubTemaDTO();
		ClienteServiciosDataservice clienteServicios = null;
		
		try{
			dominioDataServices = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_DATASERVICES);
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_SUBTEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_GET_BY_ID_SUBTEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(subTemaId);
			
			clienteServicios = new ClienteServiciosDataservice(URL, 
					listaParametros);
			respuestaServicio = clienteServicios.consumirServicio();
			Log.getInstance().info("Respuesta XML: " + respuestaServicio, getClass());
			
			subTemaDTO = (SubTemaDTO)UtilidadesPojo.getXmlToPojo(
								respuestaServicio,
								SubTemaDTO.class
							);
		} catch(NegocioExcepcion e){
			Log.getInstance().error(e, getClass());
			throw e;
		} catch(Exception e){
			Log.getInstance().error(e, getClass());
			throw new NegocioExcepcion(ConstantesSerCappital.MENSAJE_ERROR_BROKER);
		}
		
		return subTemaDTO;
	}
	
	/**
	 * Metodo encargado de eliminar un subtema de base de datos.
	 * 
	 * @param subTemaId
	 * @throws NegocioExcepcion
	 */
	public void deleteSubtemas(int subTemaId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_SUBTEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_DELETE_SUBTEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(subTemaId);
			
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
	 * Metdo encargado de insertar un registro en base de datos de un subtema.
	 * 
	 * @param temaId
	 * @param nombre
	 * @param descripcion
	 * @param estadoId
	 * @throws NegocioExcepcion
	 */
	public void insertSubtemas(int temaId, String nombre, String descripcion, int estadoId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_SUBTEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_INSERT_SUBTEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(temaId);
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
	 * Metodo encargado de actualizar un registro de un subtema.
	 * 
	 * @param subTemaId
	 * @param temaId
	 * @param nombre
	 * @param descripcion
	 * @param estadoId
	 * @throws NegocioExcepcion
	 */
	public void updateSubtemas(int subTemaId, int temaId, String nombre, String descripcion, int estadoId) throws NegocioExcepcion{

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
			dominioServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.DOMINIO_SUBTEMAS);
			nombreServicio = PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.NOMBRE_SERVICIO_UPDATE_SUBTEMAS);
			timeout = Integer.parseInt(PropiedadesManager.getInstance().getProperty(ConstantesSerCappital.TIMEOUT_DATASERVICES));
			
			URL = dominioDataServices+dominioServicio+nombreServicio;
			Log.getInstance().info("URL: " + URL + ", timeOut: " + timeout, getClass());
			
			listaParametros = new LinkedList<Object>();
			listaParametros.add(subTemaId);
			listaParametros.add(temaId);
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
	

}
