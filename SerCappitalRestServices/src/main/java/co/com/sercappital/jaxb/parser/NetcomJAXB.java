package co.com.sercappital.jaxb.parser;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("rawtypes")
public class NetcomJAXB {
	
	// singleton pattern: one instance per class.
	private static Map<Class, NetcomJAXB> singletonMap = new HashMap<Class, NetcomJAXB>();
    //private Class clazz;
    private JAXBContext jc;

    // thread-local pattern: one marshaller/unmarshaller instance per thread
    private ThreadLocal<Marshaller> marshallerThreadLocal = new ThreadLocal<Marshaller>();
    private ThreadLocal<Unmarshaller> unmarshallerThreadLocal = new ThreadLocal<Unmarshaller>();

    // The static singleton getter needs to be thread-safe too, 
    // so this method is marked as synchronized.
    public static synchronized NetcomJAXB get(Class clazz) throws JAXBException {
        NetcomJAXB jaxb = singletonMap.get(clazz);
        if (jaxb == null) {
            jaxb = new NetcomJAXB(clazz);
            singletonMap.put(clazz, jaxb);
        }
        return jaxb;
    }

    // the constructor needs to be private, 
    // because all instances need to be created with the get method.
    private NetcomJAXB(Class clazz) throws JAXBException {
        this.jc = JAXBContext.newInstance(clazz);
    }

    /**
     * Gets/Creates a marshaller (thread-safe)
     *
     * @throws JAXBException
     */
    public Marshaller getMarshaller() throws JAXBException {
        Marshaller m = marshallerThreadLocal.get();
        if (m == null) {
//            JAXBContext jc = JAXBContext.newInstance(clazz);
            m = jc.createMarshaller();
            marshallerThreadLocal.set(m);
        }
        return m;
    }

    /**
     * Gets/Creates an unmarshaller (thread-safe)
     *
     * @throws JAXBException
     */
    public Unmarshaller getUnmarshaller() throws JAXBException {
        Unmarshaller um = unmarshallerThreadLocal.get();
        if (um == null) {
//            JAXBContext jc = JAXBContext.newInstance(clazz);
            um = jc.createUnmarshaller();
            unmarshallerThreadLocal.set(um);
        }
        return um;
    }

}
