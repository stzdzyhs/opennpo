package org.opennpo.mpres;

import java.io.File;
import java.util.logging.Logger;
import org.opennpo.conf.Configuration;
import org.opennpo.conf.ConfigurationManager;

/**
 *
 * @author Nate Jones
 */
public final class Conf {
    
    public static final String LNF_KEY = "LookAndFeel";
    
    private static final Logger log;
    private static final File baseDir;
    private static Configuration mpresConfig;
    
    static{
        log = Logger.getLogger(Conf.class.getName());
        baseDir = new File(".");
        mpresConfig = 
                ConfigurationManager.getAppConfig().getPackageSubset(Conf.class);
    }
    
    public static File getBaseDir() {
        return baseDir;
    }
    
    public static Configuration getConfig(){
        return mpresConfig;
    }
    
    public static String get(String key, String def){
        return getConfig().get(key, def);
    }
    
    static void startUp(){
        log.info("***Start up***");
    }
    
    static void shutDown(){
        log.info("***Shut Down!***");
    }
}
