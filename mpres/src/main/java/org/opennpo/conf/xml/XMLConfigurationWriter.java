package org.opennpo.conf.xml;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.opennpo.conf.*;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Nate Jones
 */
public class XMLConfigurationWriter extends XMLConstants implements ConfigurationWriter{
    
    @Override
    public void write(Configuration conf, OutputStream out) {
        try {
            Marshaller marsh = getJAXBContext(conf).createMarshaller();
            XMLOutputFactory fac = XMLOutputFactory.newInstance();
            fac.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, Boolean.TRUE);
            XMLStreamWriter writer = fac.createXMLStreamWriter(out);
            System.out.println("Writer Implementation: "+writer.getClass());
            writer.writeStartDocument();
            writer.writeStartElement(NS, ROOT_EL);
            writer.writeNamespace(PFX, NS);
            for(Map.Entry<String, Object> entry : conf.entrySet()){
                writeEntry(entry, writer, marsh);
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
        } catch (Exception ex) {
            Logger.getLogger(XMLConfigurationWriter.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        
    }
    
    private void writeEntry(Map.Entry<String, Object> entry, 
            XMLStreamWriter writer, Marshaller marsh) 
            throws XMLStreamException, JAXBException {
        String key = entry.getKey();
        Object value = entry.getValue();
        writer.writeStartElement(NS, ENTRY_EL);
        writer.writeAttribute(NS, KEY_ATT, key);
        if(value!= null){
            Class clazz = value.getClass();
            writer.writeAttribute(NS, CLASS_ATT, clazz.getName());
            if((value instanceof Number)||(value instanceof Boolean)
                    ||(value instanceof String)){
                writer.writeCharacters(value.toString());
            }
            else{
                marsh.marshal(value, writer);
            }
        }
        else{
            writer.writeAttribute(NS, ISNULL_ATT, "true");
        }
        writer.writeEndElement();
    }
}
