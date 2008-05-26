package org.opennpo.conf;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Nate Jones
 */
class ConfigurationPrefixImpl extends AbstractConfiguration{
    private Configuration root;
    private String prefix;
    
    ConfigurationPrefixImpl(Configuration root, String prefix){
        this.root = root;
        this.prefix = prefix;
    }

    @Override
    public Configuration getPackageSubset(Class clazz) {
        return root.getPackageSubset(clazz);
    }

    @Override
    public Configuration getClassSubset(Class clazz) {
        return root.getClassSubset(clazz);
    }

    @Override
    public Configuration getPrefixSubset(String prefix) {
        return root.getPrefixSubset(this.prefix+prefix);
    }

    @Override
    public Configuration getBase() {
        return root;
    }

    @Override
    public int size() {
        int count=0;
        synchronized(getSyncObject()){
            for(String key : root.keySet()){
                if((key!=null)&&(key.startsWith(prefix))){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean containsKey(Object key) {
        return root.containsKey(prefix+key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public Object get(Object key) {
        return root.get(prefix+key);
    }

    @Override
    public Object put(String key, Object value) {
        return root.put(prefix+key, value);
    }

    @Override
    public Object remove(Object key) {
        return root.remove(prefix+key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        synchronized(getSyncObject()){
            for(Map.Entry entry : m.entrySet()){
                put(""+entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public void clear() {
        synchronized(getSyncObject()){
            Set<String> keys = keySet();
            for(String key : keys){
                root.remove(key);
            }
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> keys = new HashSet<String>();
        synchronized(getSyncObject()){
            for(String key : root.keySet()){
                if(key.startsWith(prefix)){
                    keys.add(key);
                }
            }
        }
        return Collections.unmodifiableSet(keys);
    }

    @Override
    public Collection<Object> values() {
        Vector values = new Vector();
        synchronized(getSyncObject()){
            for(Entry<String,Object> entry : root.entrySet()){
                if(entry.getKey().startsWith(prefix)){
                    values.add(entry.getValue());
                }
            }
        }
        return Collections.unmodifiableCollection(values);
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        HashSet<Entry<String, Object>> entries = new HashSet<Entry<String,Object>>();
        synchronized(getSyncObject()){
            for(Entry<String,Object> entry : root.entrySet()){
                if(entry.getKey().startsWith(prefix)){
                    entries.add(entry);
                }
            }
        }
        return Collections.unmodifiableSet(entries);
    }

    @Override
    public Object getSyncObject() {
        return root.getSyncObject();
    }

}
