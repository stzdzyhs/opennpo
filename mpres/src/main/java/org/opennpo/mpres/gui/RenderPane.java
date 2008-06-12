package org.opennpo.mpres.gui;

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
        layers.setBackground(Color.BLACK);
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                layers.setSize(getSize());
                for(Component comp : layers.getComponents()){
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
        ret.setBackground(getBackground());
        return ret;
    }
    
    protected void releaseLayer(RenderLayer layer){
        layer.setScriptItem(null);
        cachedLayers.push(layer);
    }
    
    public void addLayer(ScriptItem item){
        RenderLayer layer = getAvailableLayer(item);
        layers.add(layer, new Integer(layers.getComponentCount()+1));
        layer.setLocation(0,0);
        layer.setSize(getSize());
    }
}