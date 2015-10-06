package co.com.sercappital.jaxb.parser;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 
 * @author Juan David Uribe Ruiz
 * @date 2012/12/22
 *
 */
public class UtilidadesPojo {
	
    /**
     * Metodo encargado de generar un XML a partir de
     * un Pojo.
     * 
     * @param xml
     * @param clase
     * @return
     * @throws NegocioExcepcion
     */
	@SuppressWarnings("rawtypes")
    public static Object getXmlToPojo(String xml, Class clase) 
    		throws Exception {
		try{
			XMLReader reader = XMLReaderFactory.createXMLReader();
			
			NetcomJAXB netcomJAXB = NetcomJAXB.get(clase);
			
			Unmarshaller unmarshaller = netcomJAXB.getUnmarshaller();
			SAXSource source = null;
			
			xml = xml.replace("ns:return", "response");
			
			NamespaceFilter inFilter = new NamespaceFilter(null, false);
			inFilter.setParent(reader);
			
			InputSource is = new InputSource(
			new ByteArrayInputStream(xml.getBytes("utf-8")));
			source = new SAXSource(inFilter, is);
			
			return unmarshaller.unmarshal(source);
		} catch(Exception e){
			throw e;
		}
	}
    
    /**
     * Metodo que obtiene los datos de un XML y los entrega en 
     * un Pojo.
     * 
     * @param objeto
     * @param clase
     * @return
     * @throws NegocioExcepcion
     */
    @SuppressWarnings("rawtypes")
	public static String getPojoToXml(Object objeto, Class clase) 
	throws Exception {

    	JAXBContext jaxbContext;
    	Marshaller marshallerObj;
    	String xml;
    	StringWriter stringWriter;
    	
    	try{
    		
	    	jaxbContext = JAXBContext.newInstance(clase);
	    	marshallerObj = jaxbContext.createMarshaller();
		    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    stringWriter = new StringWriter();
		    marshallerObj.marshal(objeto, stringWriter);
		    xml = stringWriter.toString();
		    xml = xml.replace("xmlns:ns2=", "xmlns=");
    	}catch(Exception e){
    		
    		throw new Exception(e);
    	}
    	return xml;
    }
    
}