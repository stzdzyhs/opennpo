package org.opennpo.conf;

import java.util.Map;

/**
 * Interface for holding all the configuration options for an application.  
 * It allows an application developer to supply default values for most values.
 * @author Nate Jones
 */
public interface Configuration extends Map<String,Object> {
    /**
     * This method gets the value referenced by the key string.  It either 
     * returns value stored with key cast to type T or it returns a new instance 
     * of clazz.
     * @param key
     * @param clazz
     * @return Either the value stored with key or it returns a new instance of clazz.
     */
    <T> T get(String key, Class<T> clazz);
    /**
     * Either returns the value stored with key or it returns def.
     * @param key
     * @param def
     * @return
     */
    String get(String key, String def);
    /**
     * Either returns the value stored with key or it returns def.
     * @param key
     * @param def
     * @return
     */
    boolean get(String key, boolean def);
    /**
     * Either returns the value stored with key or it returns def.
     * @param key
     * @param def
     * @return
     */
    int get(String key, int def);
    /**
     * Either returns the value stored with key or it returns def.
     * @param key
     * @param def
     * @return
     */
    float get(String key, float def);
    /**
     * Either returns the value stored with key or it returns def.
     * @param key
     * @param def
     * @return
     */
    long get(String key, long def);
    /**
     * Either returns the value stored with key or it returns def.
     * @param key
     * @param def
     * @return
     */
    double get(String key, double def);
    /**
     * Either returns the value stored with key cast to type T or it returns 
     * def.
     * @param key
     * @param def
     * @return
     */
    <T extends Object> T get(String key, T def);
    
    /**
     * Returns the String value stored with key or the empty string.
     * @param key
     * @return
     */
    String getString(String key);
    /**
     * Returns the boolean stored with key or false.
     * @param key
     * @return
     */
    boolean getBoolean(String key);
    /**
     * Returns the int stored with key or 0.
     * @param key
     * @return
     */
    int getInt(String key);
    /**
     * Returns the float stored with key or Float.NaN.
     * @param key
     * @return
     */
    float getFloat(String key);
    /**
     * Returns the long stored with key or 0l.
     * @param key
     * @return
     */
    long getLong(String key);
    /**
     * Returns the double stored with key or Double.NaN.
     * @param key
     * @return
     */
    double getDouble(String key);
    
    /**
     * Stores value and associates it with key.
     * @param key
     * @param value
     */
    void put(String key, boolean value);
    /**
     * Stores value and associates it with key.
     * @param key
     * @param value
     */
    void put(String key, int value);
    /**
     * Stores value and associates it with key.
     * @param key
     * @param value
     */
    void put(String key, float value);
    /**
     * Stores value and associates it with key.
     * @param key
     * @param value
     */
    void put(String key, long value);
    /**
     * Stores value and associates it with key.
     * @param key
     * @param value
     */
    void put(String key, double value);
    
    /**
     * Returns a Configuration object that only has values with keys that begin 
     * with clazz.getPackage().getName()+"."
     * @param clazz
     * @return
     */
    Configuration getPackageSubset(Class clazz);
    /**
     * Returns a Configuration object that only has values with keys that begin 
     * with clazz.getName()+"."
     * @param clazz
     * @return
     */
    Configuration getClassSubset(Class clazz);
    /**
     * Returns a Configuration object that only has values with keys that begin 
     * with prefix.
     * @param prefix
     * @return
     */
    Configuration getPrefixSubset(String prefix);
    /**
     * Returns the top level Configuration object that doesn't rely on any other
     * Configuration object.
     * @return
     */
    Configuration getBase();
    
    /**
     * Gets the object that is used for multithread access.
     * @return
     */
    Object getSyncObject();
}
