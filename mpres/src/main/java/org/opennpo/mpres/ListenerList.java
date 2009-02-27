package org.opennpo.mpres;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 *
 * @author Nate Jones
 */
public class ListenerList implements List<ScriptItemListener>{
    private List<ScriptItemListener> listeners;
    public ListenerList(){
        listeners = Collections.synchronizedList(new Vector<ScriptItemListener>());
    }
    
    public void fireItemSelected(ScriptItemSource src, ScriptItem item){
        synchronized(listeners){
            for(ScriptItemListener lst : listeners){
                lst.itemSelected(src, item);
            }
        }
    }
    
    public Object getSyncObject(){
        return listeners;
    }

    @Override
    public int size() {
        return listeners.size();
    }

    @Override
    public boolean isEmpty() {
        return listeners.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return listeners.contains(o);
    }

    @Override
    public Iterator<ScriptItemListener> iterator() {
        return listeners.iterator();
    }

    @Override
    public Object[] toArray() {
        return listeners.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return listeners.toArray(a);
    }

    @Override
    public boolean add(ScriptItemListener e) {
        return listeners.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return listeners.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return listeners.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends ScriptItemListener> c) {
        return listeners.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends ScriptItemListener> c) {
        return listeners.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return listeners.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return listeners.retainAll(c);
    }

    @Override
    public void clear() {
        listeners.clear();
    }

    @Override
    public ScriptItemListener get(int index) {
        return listeners.get(index);
    }

    @Override
    public ScriptItemListener set(int index, ScriptItemListener element) {
        return listeners.set(index, element);
    }

    @Override
    public void add(int index, ScriptItemListener element) {
        listeners.add(index, element);
    }

    @Override
    public ScriptItemListener remove(int index) {
        return listeners.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return listeners.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return listeners.lastIndexOf(o);
    }

    @Override
    public ListIterator<ScriptItemListener> listIterator() {
        return listeners.listIterator();
    }

    @Override
    public ListIterator<ScriptItemListener> listIterator(int index) {
        return listeners.listIterator(index);
    }

    @Override
    public List<ScriptItemListener> subList(int fromIndex, int toIndex) {
        return listeners.subList(fromIndex, toIndex);
    }
}
