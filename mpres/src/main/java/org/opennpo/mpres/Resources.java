/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mpres;

import java.net.URL;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Nate Jones
 */
public final class Resources {
    private static Logger log = Logger.getLogger(Resources.class.getName());
    public static ImageIcon getIcon(String path){
        return getIcon(Resources.class, path);
    }
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
