package org.opennpo.mpres.gui;

import java.awt.Component;
import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import org.opennpo.mpres.ScriptItem;

/**
 * A graphical component for rendering several ScriptItems in a layered layout.
 * @author Nate Jones
 */
public class RenderPane extends JComponent{
    private Logger log = Logger.getLogger(RenderPane.class.getName());
    private JLayeredPane layers;
    private Deque<RenderLayer> cachedLayers;
    
    public RenderPane(){
        layers = new JLayeredPane();
        this.add(layers);
        cachedLayers = new LinkedList<RenderLayer>();
    }
    
    protected RenderLayer getAvailableLayer(ScriptItem item){
        RenderLayer ret = cachedLayers.poll();
        if(ret==null){
            ret = new RenderLayer();
        }
        ret.setScriptItem(item);
        return ret;
    }
    
    protected void releaseLayer(RenderLayer layer){
        layer.setScriptItem(null);
        cachedLayers.push(layer);
    }
    
    public void addLayer(ScriptItem item){
        RenderLayer layer = getAvailableLayer(item);
        layers.add(layer);
    }
    
    public void putLayer(int index, ScriptItem item){
        RenderLayer layer = getAvailableLayer(item);
        layers.setLayer(layer, index);
    }
    
    public void removeLayer(int index){
        RenderLayer layer = (RenderLayer)layers.getComponent(index);
        layers.remove(layer);
        cachedLayers.add(layer);
    }
    
    public void clear(){
        for(Component comp : layers.getComponents()){
            if(comp instanceof RenderLayer){
                RenderLayer layer = (RenderLayer)comp;
                layers.remove(comp);
                releaseLayer(layer);
            }
        }
    }
    
    public ScriptItem getItem(int index){
        Component comp = layers.getComponent(index);
        if(comp instanceof RenderLayer){
            return ((RenderLayer)comp).getScriptItem();
        }
        else{
            return null;
        }
    }
    
    public int getItemCount(){
        return layers.getComponentCount();
    }
}