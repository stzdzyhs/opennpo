package org.opennpo.mpres;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opennpo.conf.Configuration;
import org.opennpo.conf.ConfigurationManager;
import org.opennpo.conf.ConfigurationReader;
import org.opennpo.conf.ConfigurationWriter;
import org.opennpo.conf.xml.XMLConfigurationReader;
import org.opennpo.conf.xml.XMLConfigurationWriter;

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
        try {
            log.info("Reading Coniguration...");
            File f = new File(getBaseDir(), "configuration.xml");
            if(f.exists()){
                log.info("Configuration file '"+f.getCanonicalPath()+"' found.");
                ConfigurationReader reader = new XMLConfigurationReader();
                reader.read(ConfigurationManager.getAppConfig(), new FileInputStream(f));
            }
            else{
                log.info("Configuration file '" + f.getCanonicalPath() + "' does not exist.");
            }
        } catch (IOException ex) {
            Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("Done Reading Configuration.");
    }
    
    static void shutDown(){
        OutputStream out = null;
        try {
            log.info("Writing Configuration...");
            ConfigurationWriter confWriter = new XMLConfigurationWriter();
            File f = new File(getBaseDir(), "configuration.xml");
            out = new FileOutputStream(f);
            log.info("Configuration File: "+f.getCanonicalPath());
            confWriter.write(ConfigurationManager.getAppConfig(), out);
            log.info("Done Writing Configuration.");
        } catch (Exception ex) {
            Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(out!=null)
                    out.close();
            } catch (IOException ex) {
                Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log.info("***Shut Down!***");
    }
}
