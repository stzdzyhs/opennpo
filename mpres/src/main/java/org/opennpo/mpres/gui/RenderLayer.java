package org.opennpo.mpres.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Renderer;
import org.opennpo.mpres.ScriptItem;

/**
 * 
 * @author Nate Jones
 */
public class RenderLayer extends Component{
    private ScriptItem item;
    private Map<Class, Renderer> renderers;
    private Component child;
    private Dimension size;
    
    public RenderLayer(){
        item = null;
        renderers = new HashMap<Class, Renderer>();
        child = null;
        setIgnoreRepaint(false);
        size = new Dimension();
    }
    
    public void setScriptItem(ScriptItem item){
        this.item = item;
        if(item!=null){
            Renderer renderer = renderers.get(item.getRenderer());
            if(renderer == null){
                try {
                    renderer = item.getRenderer().newInstance();
                } catch (InstantiationException ex) {
                    Logger.getLogger(RenderLayer.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(RenderLayer.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
            renderer.setValue(item, true);
            child = renderer.getComponent();
            child.setSize(getSize());
        }
    }
    
    public ScriptItem getScriptItem(){
        return item;
    }
    
    @Override
    public void paint(Graphics grp){
        if(child!=null){
            child.paint(grp);
        }
    }
    
    @Override
    public void setSize(Dimension d){
        size = d;
        if(child !=null)
            child.setSize(d);
    }
    
    @Override
    public Dimension getSize(){
        return size;
    }
}
