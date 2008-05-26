package org.opennpo.conf.xml;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.opennpo.conf.Configuration;

/**
 *
 * @author nate
 */
public abstract class XMLConstants {
    static final String NS = "http://www.opennpo.org/conf";
    static final String ROOT_EL = "configuration";
    static final String ENTRY_EL = "entry";
    static final String KEY_ATT = "key";
    static final String ISNULL_ATT = "isNull";
    static final String CLASS_ATT = "class";
    static final String PFX = "conf";
    
    protected static JAXBContext getJAXBContext(Configuration conf) throws JAXBException{
        JAXBContext ctx = null;
        Set<Class> classes = new HashSet<Class>();
        for(Object o : conf.values()){
            classes.add(o.getClass());
        }
        ctx = JAXBContext.newInstance(classes.toArray(new Class[0]));
        return ctx;
    }
}
