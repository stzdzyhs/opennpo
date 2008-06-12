package org.opennpo.conf.xml;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.opennpo.conf.Configuration;
import org.opennpo.conf.ConfigurationReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nate Jones
 */
public class XMLConfigurationReader extends XMLConstants implements ConfigurationReader{
    private static Logger log = Logger.getLogger(XMLConfigurationReader.class.getName());
    @Override
    public void read(Configuration conf, InputStream input) {
        try {
            DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
            fac.setNamespaceAware(true);
            fac.setIgnoringComments(true);
            DocumentBuilder builder = fac.newDocumentBuilder();
            Document doc = builder.parse(input);
            doc.normalizeDocument();
            Element root = doc.getDocumentElement();
            NodeList entries = root.getChildNodes();
            Set<Class> classes = new HashSet<Class>();
            Element ele;
            String att;
            for(int i = 0;i<entries.getLength();++i){
                ele = (Element)entries.item(i);
                att = ele.getAttributeNS(NS, CLASS_ATT);
                if(!isEmpty(att)){
                    classes.add(Class.forName(att));
                }
            }
            JAXBContext ctx = JAXBContext.newInstance(classes.toArray(new Class[0]));
            Unmarshaller marsh = ctx.createUnmarshaller();
            for(int i = 0;i<entries.getLength();++i){
                ele = (Element)entries.item(i);
                loadElement(conf, ele, marsh);
            }
        } 
        catch (Exception ex) {
            Logger.getLogger(XMLConfigurationReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void loadElement(Configuration conf, Element ele, Unmarshaller marsh){
        try{
            String key = ele.getAttributeNS(NS, KEY_ATT);
            String isNull = ele.getAttributeNS(NS, ISNULL_ATT);
            String cname = ele.getAttributeNS(NS, CLASS_ATT);
            if("true".equalsIgnoreCase(isNull)){
                conf.put(key, null);
            }
            else{
                Class clazz = Class.forName(cname);
                if(Number.class.isAssignableFrom(clazz)){
                    conf.put(key, parseNumber(clazz, ele.getTextContent()));
                }
                else if(Boolean.class.equals(clazz)){
                    conf.put(key, new Boolean("true".equalsIgnoreCase(ele.getTextContent())));
                }
                else if(String.class.equals(clazz)){
                    conf.put(key, ele.getTextContent());
                }
                else {
                    conf.put(key, marsh.unmarshal(ele.getFirstChild()));
                }
            }
        }
        catch(Exception ex){
            Logger.getLogger(XMLConfigurationReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Number parseNumber(Class clazz, String num){
        return new Double(Double.parseDouble(num));
    }
    
    private static boolean isEmpty(String str){
        return (str==null)||(str.equals(""));
    }
}
