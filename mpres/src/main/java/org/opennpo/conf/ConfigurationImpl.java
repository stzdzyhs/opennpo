package org.opennpo.conf;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Nate Jones
 */
class ConfigurationImpl extends AbstractConfiguration{
    private Map<String, Object> map;
    ConfigurationImpl(){
        map = Collections.synchronizedMap(new HashMap<String, Object>());
    }
    
    @Override
    public Configuration getPackageSubset(Class clazz) {
        Package pkg = clazz.getPackage();
        String prefix = pkg.getName()+".";
        return new ConfigurationPrefixImpl(this, prefix);
    }

    @Override
    public Configuration getClassSubset(Class clazz) {
        String prefix = clazz.getCanonicalName()+".";
        return new ConfigurationPrefixImpl(this, prefix);
    }

    @Override
    public Configuration getPrefixSubset(String prefix) {
        return new ConfigurationPrefixImpl(this, prefix);
    }

    @Override
    public Configuration getBase() {
        return this;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public Object getSyncObject() {
        return map;
    }

}
