package org.opennpo.mpres.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import org.opennpo.mpres.ScriptItem;

/**
 * A graphical component for rendering several ScriptItems in a layered layout.
 * @author Nate Jones
 */
public class RenderPane extends JPanel{
    private Logger log = Logger.getLogger(RenderPane.class.getName());
    private JLayeredPane layers;
    private Deque<RenderLayer> cachedLayers;
    
    public RenderPane(){
        setLayout(null);
        cachedLayers = new LinkedList<RenderLayer>();
        setBackground(Color.BLACK);
        layers = new JLayeredPane();
        add(layers);
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                for(Component comp : getComponents()){
                    comp.setSize(getSize());
                }
            }
        });
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
        //add(layer);
        layer.setLocation(0,0);
        layer.setSize(getSize());
    }
    /*
    public void putLayer(int index, ScriptItem item){
        RenderLayer layer = getAvailableLayer(item);
        layers.setLayer(layer, index);
    }
    
    public void removeLayer(int index){
        RenderLayer layer = (RenderLayer)layers.getComponent(index);
        layers.remove(layer);
        cachedLayers.add(layer);
    }
    
    public void clearLayers(){
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
    }*/
}