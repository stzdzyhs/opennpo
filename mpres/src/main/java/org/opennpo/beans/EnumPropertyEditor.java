package org.opennpo.beans;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.util.EnumSet;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Nate Jones
 */
public class EnumPropertyEditor implements PropertyEditor{
    private List<Enum> range;
    private Enum value;
    private String[] tags;
    
    public EnumPropertyEditor(Class<? extends Enum> clazz){
        value = null;
        init(EnumSet.allOf(clazz));
    }
    
    protected void init(EnumSet set){
        range = new Vector<Enum>(set);
        tags = new String[range.size()];
        int i=0;
        for(Enum e : range){
            tags[i++]=e.name();
        }
    }
    
    @Override
    public void setValue(Object value) {
        this.value = (Enum) value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public boolean isPaintable() {
        return false;
    }

    @Override
    public void paintValue(Graphics gfx, Rectangle box) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getJavaInitializationString() {
        return null;
    }

    @Override
    public String getAsText() {
        return value==null?"":value.name();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if((text==null)||("".equals(text))){
            value = null;
        }
        else{
            if(range.isEmpty()){
                throw new IllegalArgumentException("You cannot SetAsText without a range.");
            }
            value = Enum.valueOf(range.get(0).getClass(), text);
        }
    }

    @Override
    public String[] getTags() {
        return tags;
    }

    @Override
    public Component getCustomEditor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean supportsCustomEditor() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        
    }
    
}
