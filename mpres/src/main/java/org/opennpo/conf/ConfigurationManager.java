package org.opennpo.conf;

/**
 *
 * @author Nate Jones
 */
public final class ConfigurationManager {
    private static Configuration appConfig = null;
    
    public static synchronized Configuration getAppConfig(){
        if(appConfig==null){
            appConfig = new ConfigurationImpl();
        }
        return appConfig;
    }
    
    public static synchronized void setAppConfig(Configuration config){
        appConfig = config;
    }
}
