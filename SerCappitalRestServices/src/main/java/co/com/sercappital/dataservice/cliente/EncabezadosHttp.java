package co.com.sercappital.dataservice.cliente;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

public class EncabezadosHttp {

    private final String CONTENT_TYPE_WWW = "application/x-www-form-urlencoded";
    private final String CONTENT_TYPE_JSON = "application/json";
    private final String ACCEPT = "text/html,text/xml,text/plain";
    private final String CONNECTION = "keep-alive";
    private final String USER_AGENT = "Mozilla/5.0";
    private final String ACCEPT_CHARSET = "utf-8";
    private final String ACCEPT_LANGUAGE = "es,en,en-US,es-ES,es-MX";
    private final String CACHE_CONTROL = "no-cache";
    
    private Header[] encabezadosHttp;
    
    /**
     * Constructor de la Clase
     */
    public EncabezadosHttp() { 
        
    	encabezadosHttp = new Header[] {
                new BasicHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_WWW),
                new BasicHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON),
                new BasicHeader(HttpHeaders.ACCEPT, ACCEPT),
                new BasicHeader(HttpHeaders.CONNECTION, CONNECTION),
                new BasicHeader(HttpHeaders.USER_AGENT, USER_AGENT),
                new BasicHeader(HttpHeaders.ACCEPT_CHARSET, ACCEPT_CHARSET),
                new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, ACCEPT_LANGUAGE),
                new BasicHeader(HttpHeaders.CACHE_CONTROL, CACHE_CONTROL)
            };
    	
    }
    
    /**
     * Método encargado de retornar un Array con los encabezados Http a usar
     * @return
     */
    public Header[] getEncabezadosHttp() {        
        return encabezadosHttp;        
    }

    /**
     * Metodo que permite definir los encabezados Http que se necesiten
     * en el momento de la ejecución al llamado del Rest-Service.
     * @param hmParametros
     */
    public void setEncabezadoHttpPersonalizado(HashMap<String, String> hmParametros) { 
        
    	encabezadosHttp = null;
        if (hmParametros != null) {
        	if (hmParametros.size() > 0) {
                int ind = 0;
                int numeroDeEncabezados = hmParametros.size();
                
                encabezadosHttp = new Header[numeroDeEncabezados];
                for (String clave : hmParametros.keySet()) {                
                    BasicHeader basicHeader = new 
                            BasicHeader(clave, hmParametros.get(clave));
                    encabezadosHttp[ind] = basicHeader;
                    ind ++;                
                }        		
        	}
        }
        
    }
    
}

