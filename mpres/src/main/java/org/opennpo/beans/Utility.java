package org.opennpo.beans;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditorManager;
import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nate Jones
 */
public final class Utility {
    
    private static final Logger log = Logger.getLogger(Utility.class.getName());
    
    public static void setupBeanInfo(Class clazz){
        log.fine("Setting up: "+clazz.getName());
        try {
            BeanInfo nfo = Introspector.getBeanInfo(clazz);
            processAnnotations(nfo.getBeanDescriptor(), clazz.getAnnotations());
            if(nfo.getPropertyDescriptors()!=null){
                for(PropertyDescriptor desc : nfo.getPropertyDescriptors()){
                    if("class".equals(desc.getName())){
                        desc.setExpert(true);
                    }
                    processAnnotations(desc,desc.getReadMethod().getAnnotations());
                }
            }
            for(Class c : clazz.getClasses()){
                setupBeanInfo(c);
            }
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
            else if(ann instanceof Expert){
                desc.setExpert(true);
            }
            else if(ann instanceof PropertyEditorAnnotation){
                Class bean = ((BeanDescriptor)desc).getBeanClass();
                Class editor = ((PropertyEditorAnnotation)ann).value();
                PropertyEditorManager.registerEditor(bean, editor);
            }
        }
    }
}
