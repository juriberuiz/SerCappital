package co.com.sercappital.propiedades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase para manejar archivo de propiedades de la aplicación
 *  
 * @author Juan David Uribe Ruiz
 * @since 2015/09/22
 *
 */
public class PropiedadesManager {

	private static PropiedadesManager INSTANCE = null;
	private Properties properties;
	
	/** Indica la ruta al archivo de propiedades */
	private static final String ARCHIVO_PROPIEDADES = "configuracion/propiedades.properties";
	
	private PropiedadesManager(){
		
		cargarArchivo(ARCHIVO_PROPIEDADES);
	}
	
	private static void createInstance() {
        synchronized(PropiedadesManager.class) {
            if (INSTANCE == null) { 
                INSTANCE = new PropiedadesManager();
            }
        }
    }
	
	public static PropiedadesManager getInstance() {
        if (INSTANCE == null) {
        	createInstance();
        }
        return INSTANCE;
    }

	/**
	 * 
	 * Nombre del método:       getProperty
	 * Descripción:             <i>Método encargado de retornar el valor de la propiedad</i>
	 * @author                  César Sierra
	 * @param key
	 * @return
	 *
	 *  MODIFICACIONES:
	 * 
	 *  Fecha:                  31/08/2014
	 *  Descripción:            Creación del método
	 */
	public String getProperty(String key){

		return properties.getProperty(key);
	}
	
	/**
	 * 
	 * Nombre del método:       cargarArchivo
	 * Descripción:             <i>Método encargado de cargar el archivo de propiedades</i>
	 * @author                  César Sierra
	 * @param archivo
	 * @throws Exception
	 *
	 *  MODIFICACIONES:
	 * 
	 *  Fecha:                  31/08/2014
	 *  Descripción:            Creación del método
	 */
	private void cargarArchivo(String archivo){
		File file;
		InputStream inputStream = null;
		try {
			properties = new Properties();
			file = new File(archivo);
			inputStream = new FileInputStream(file);
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					
				}
			}
		}
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo properties
	 * 
	 * @return properties Valor del atributo
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Metodo encargado de asignar el valor del atributo properties
	 * 
	 * @param properties
	 *            Valor a asignar al atributo properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
}
