package org.opennpo.mpres;

import java.net.URL;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 * Static class for loading resources in mpres.
 * @author Nate Jones
 */
public final class Resources {
    private static Logger log = Logger.getLogger(Resources.class.getName());
    
    /**
     * Loads an Icon from a path relative to Resources.class.
     * @param path
     * @return
     */
    public static ImageIcon getIcon(String path){
        return getIcon(Resources.class, path);
    }
    
    /**
     * Loads an Icon from the supplied path relative to the supplied class.
     * @param clazz
     * @param path
     * @return
     */
    public static ImageIcon getIcon(Class clazz, String path){
        URL url = clazz.getResource(path);
        if(url!=null){
            return new ImageIcon(url);
        }
        else{
            log.severe("Couldn't find '"+path+"' using classloader from "+clazz.getName());
            return null;
        }
    }
}
