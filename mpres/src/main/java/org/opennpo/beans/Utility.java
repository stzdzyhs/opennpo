/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.beans;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nate Jones
 */
public final class Utility {
    public static void setupBeanInfo(Class clazz){
        try {
            System.out.println("hello setupBeanInfo");
            BeanInfo nfo = Introspector.getBeanInfo(clazz);
            processAnnotations(nfo.getBeanDescriptor(), clazz.getAnnotations());
        } catch (IntrospectionException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void processAnnotations(FeatureDescriptor desc, Annotation[] anns){
        if((anns==null)||(desc==null)) return;
        for(Annotation ann : anns){
            if(ann instanceof DisplayName){
                desc.setDisplayName(((DisplayName)ann).value());
            }
        }
    }
}