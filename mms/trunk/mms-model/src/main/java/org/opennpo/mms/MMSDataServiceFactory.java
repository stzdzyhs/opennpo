package org.opennpo.mms;

import java.beans.BeanInfo;
import java.util.HashMap;
import java.util.Map;
import org.opennpo.mms.jdbc.JDBCMMSDataServiceFactory;


/**
 * 
 * @author Nate Jones
 */
public abstract class MMSDataServiceFactory {
    
    public abstract MMSDataService createService(Object configurationBean);
    public abstract String getName();
    public abstract BeanInfo getConfigurationBeanInfo();
    
    private static Map<String, MMSDataServiceFactory> mFactories;
    
    static{
        mFactories = new HashMap<String, MMSDataServiceFactory>();
        mFactories.put("JDBC", new JDBCMMSDataServiceFactory());
    }
    
    public static Map<String, MMSDataServiceFactory> getFactories(){
        return mFactories;
    }
    
    public static MMSDataServiceFactory getFactory(String name){
        return mFactories.get(name);
    }

}
