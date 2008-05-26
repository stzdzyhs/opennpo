package org.opennpo.conf;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract implementation of the Configuration interface.  Makes implementing
 * the full interface much less tedious.
 * @author Nate Jones
 */
public abstract class AbstractConfiguration implements Configuration {
    @Override
    public <T> T get(String key, Class<T> clazz){
        Object o = get(key);
        if((o!=null)&&(clazz.isAssignableFrom(o.getClass()))){
            return (T)o;
        }
        else{
            try {
                return clazz.newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(AbstractConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AbstractConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    @Override
    public String get(String key, String def){
        Object o = get(key);
        if(o==null){
            return def;
        }
        else{
            return o.toString();
        }
    }
    
    @Override
    public boolean get(String key, boolean def){
        Object o = get(key);
        if(o instanceof Boolean){
            return ((Boolean)o).booleanValue();
        }
        else{
            return def;
        }
    }
    
    @Override
    public int get(String key, int def){
        Object o = get(key);
        if(o instanceof Number){
            return ((Number)o).intValue();
        }
        else{
            return def;
        }
    }
    
    @Override
    public float get(String key, float def){
        Object o = get(key);
        if(o instanceof Number){
            return ((Number)o).floatValue();
        }
        else{
            return def;
        }
    }
    
    @Override
    public long get(String key, long def){
        Object o = get(key);
        if(o instanceof Number){
            return ((Number)o).longValue();
        }
        else{
            return def;
        }
    }
    
    @Override
    public double get(String key, double def){
        Object o = get(key);
        if(o instanceof Number){
            return ((Number)o).doubleValue();
        }
        else{
            return def;
        }
    }
    
    @Override
    public <T extends Object> T get(String key, T def){
        Object o = get(key);
        if(o!=null){
            try{
                return (T)o;
            }
            catch(Throwable th){
                Logger.getLogger(AbstractConfiguration.class.getName()).log(Level.SEVERE, null, th);
            }
        }
        return def;
    }
    
    
    @Override
    public String getString(String key){
        return get(key, "");
    }
    
    @Override
    public boolean getBoolean(String key){
        return get(key, false);
    }
    
    @Override
    public int getInt(String key){
        return get(key, 0);
    }
    
    @Override
    public float getFloat(String key){
        return get(key, Float.NaN);
    }
    
    @Override
    public long getLong(String key){
        return get(key, 0l);
    }
    
    @Override
    public double getDouble(String key){
        return get(key, Double.NaN);
    }
    
    
    @Override
    public void put(String key, boolean value){
        put(key, new Boolean(value));
    }
    
    @Override
    public void put(String key, int value){
        put(key, new Integer(value));
    }
    
    @Override
    public void put(String key, float value){
        put(key, new Float(value));
    }
    
    @Override
    public void put(String key, long value){
        put(key, new Long(value));
    }
    
    @Override
    public void put(String key, double value){
        put(key, new Double(value));
    }
    
}
