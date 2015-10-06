package co.com.sercappital.utilidades;

import com.google.gson.Gson;

/**
 * Utilidad usada para el parseo entre DTO's y Json
 * 
 * @author Juan David Uribe Ruiz
 * @since 2015/09/22
 * 
 */
public class UtilidadJson {
	
	/**
	 * Metodo encargado de parsear un DTO a un Json
	 * 
	 * @param objeto
	 * @return
	 * @throws Exception
	 */
	public static String dtoToJson(Object objeto) throws Exception{
		Gson gson = new Gson();
		return gson.toJson(objeto);
	}
	
	/**
	 * Metodo encargado de parsear un Json en un DTO
	 * 
	 * @param json
	 * @param clase
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object jsonToDto(String json, Class clase) throws Exception{
		Gson gson = new Gson();
		return gson.fromJson(json, clase);
	}

}
