package org.opennpo.conf;

import java.util.Map;

/**
 *
 * @author Nate Jones
 */
public interface Configuration extends Map<String,Object> {
    <T> T get(String key, Class<T> clazz);
    String get(String key, String def);
    boolean get(String key, boolean def);
    int get(String key, int def);
    float get(String key, float def);
    long get(String key, long def);
    double get(String key, double def);
    <T extends Object> T get(String key, T def);
    
    String getString(String key);
    boolean getBoolean(String key);
    int getInt(String key);
    float getFloat(String key);
    long getLong(String key);
    double getDouble(String key);
    
    void put(String key, boolean value);
    void put(String key, int value);
    void put(String key, float value);
    void put(String key, long value);
    void put(String key, double value);
    
    Configuration getPackageSubset(Class clazz);
    Configuration getClassSubset(Class clazz);
    Configuration getPrefixSubset(String prefix);
    Configuration getBase();
    
    Object getSyncObject();
}
