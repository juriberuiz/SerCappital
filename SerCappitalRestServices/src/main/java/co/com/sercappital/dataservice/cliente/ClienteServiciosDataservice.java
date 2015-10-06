package co.com.sercappital.dataservice.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import co.com.sercappital.constantes.ConstantesSerCappital;
import co.com.sercappital.excepciones.NegocioExcepcion;
import co.com.sercappital.utilidad.logger.Log;

public class ClienteServiciosDataservice {
	
	final String SEPARADOR_VALORES_RESTSERVICE = "/";
	final String ENCODE_ESPACIO = "%20";
	final static List<Integer> codRespuestaExitosos = Arrays.asList(200, 201, 202, 203, 204, 205, 206, 207, 208, 226);
	
	StringBuilder endpoint;
	
	/**
	 * 
	 * @param url
	 * @param listaParametros
	 */
	public ClienteServiciosDataservice(String url,
            LinkedList<?> listaParametros) {
		
		endpoint = new StringBuilder();
		endpoint.append(url).append(SEPARADOR_VALORES_RESTSERVICE);
		
		int ind = 1;
		for(Object object : listaParametros) {
			endpoint.append(object);
			if (ind < listaParametros.size()) {
				endpoint.append(SEPARADOR_VALORES_RESTSERVICE);
			}
			ind++;
		}
		endpoint = new StringBuilder(endpoint.toString().replaceAll(" ", ENCODE_ESPACIO));
    }

	/**
	 * 
	 * @param urlEndPoint
	 * @param parametrosEndPoint
	 */
	public ClienteServiciosDataservice(String urlEndPoint,
            String... parametrosEndPoint) {
				
		Log.getInstance().info("Constructor ClienteServiciosDataservice", getClass());
		
        if (parametrosEndPoint.length > 0) {
        	endpoint = new StringBuilder(urlEndPoint).append("/");
            int ind = 1;
            for (String parametro : parametrosEndPoint) {
            	endpoint.append(parametro);
                if (ind < parametrosEndPoint.length) {
                	endpoint.append(SEPARADOR_VALORES_RESTSERVICE);
                }
                ind++;
            }
        } else {
        	endpoint = new StringBuilder(urlEndPoint);
        }
        endpoint = new StringBuilder(endpoint.toString().replaceAll(" ", ENCODE_ESPACIO));
	}
	
	/**
	 * 
	 * @return
	 */
	public String consumirServicio() throws NegocioExcepcion{
		Log.getInstance().info("Método consumirServicio", getClass());
		
		StringBuilder sbResultadoConsumoServicio = new StringBuilder();
		String respuestaServicio = "";
		
    	try {
   		 
    		EncabezadosHttp encabezadosHttp = new EncabezadosHttp();
			DefaultHttpClient httpClient = new DefaultHttpClient();
    		
    		HttpGet getRequest = new HttpGet(endpoint.toString());
    		getRequest.setHeaders(encabezadosHttp.getEncabezadosHttp());
     
    		HttpResponse response = httpClient.execute(getRequest);
    		
     
    		if (!codRespuestaExitosos.contains(response.getStatusLine().getStatusCode())) {
    			throw new RuntimeException("Falló : Código de Error HTTP: "
    			   + response.getStatusLine().getStatusCode());
    		} else {
    			Log.getInstance().info("Respuesta Correcta: " + 
    					response.getStatusLine().getStatusCode(), getClass());
    		}
     
    		BufferedReader br = new BufferedReader(
                             new InputStreamReader((response.getEntity().getContent())));
     
    		Log.getInstance().info("Consumiendo el servicio del Servidor...", getClass());
    		while ((respuestaServicio = br.readLine()) != null) {
    			sbResultadoConsumoServicio.append(respuestaServicio);
    		}
     
    		httpClient.getConnectionManager().shutdown();
     
    	} catch (ClientProtocolException e) {
    		Log.getInstance().error("[ClientProtocolException] ClienteServiciosDataservice -> consumirServicio", ClienteServiciosDataservice.class);
			Log.getInstance().error(e, ClienteServiciosDataservice.class);
			throw new NegocioExcepcion(ConstantesSerCappital.CODIGO_ERROR_CONSUMO_SERVICIO_REST, 
					ConstantesSerCappital.MENSAJE_ERROR_CONSUMO_SERVICIO_REST);
    	} catch (IOException e) {
    		Log.getInstance().error("[IOException] ClienteServiciosDataservice -> consumirServicio", ClienteServiciosDataservice.class);
			Log.getInstance().error(e, ClienteServiciosDataservice.class);
			throw new NegocioExcepcion(ConstantesSerCappital.CODIGO_ERROR_CONSUMO_SERVICIO_REST, 
					ConstantesSerCappital.MENSAJE_ERROR_CONSUMO_SERVICIO_REST);
    	} catch (Exception e) {
    		Log.getInstance().error("[Exception] ClienteServiciosDataservice -> consumirServicio", ClienteServiciosDataservice.class);
			Log.getInstance().error(e, ClienteServiciosDataservice.class);
    		throw new NegocioExcepcion(ConstantesSerCappital.CODIGO_ERROR_CONSUMO_SERVICIO_REST, 
    				ConstantesSerCappital.MENSAJE_ERROR_CONSUMO_SERVICIO_REST);
    	}
		
		return sbResultadoConsumoServicio.toString();
		
	}
	
}
