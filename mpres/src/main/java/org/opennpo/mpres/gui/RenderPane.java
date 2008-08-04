package org.opennpo.mpres.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opennpo.Runner;
import org.opennpo.mpres.ScriptItem;

/**
 * A graphical component for rendering several ScriptItems in a layered layout.
 * @author Nate Jones
 */
public class RenderPane extends Container{
    private final Logger log = Logger.getLogger(RenderPane.class.getName());
    private Deque<RenderLayer> cachedLayers;
    private Vector<RenderLayer> layers;
    
    public RenderPane(){
        setLayout(null);
        setIgnoreRepaint(false);
        cachedLayers = new LinkedList<RenderLayer>();
        layers = new Vector<RenderLayer>();
        setBackground(Color.BLACK);
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                for(RenderLayer comp : layers){
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
        layers.remove(layer);
        layer.setScriptItem(null);
        cachedLayers.push(layer);
    }
    
    public void addLayer(ScriptItem item){
        RenderLayer layer = getAvailableLayer(item);
        add(layer);
    }
    
    @Override
    public void paint(Graphics grp){
        grp.setColor(getBackground());
        grp.fillRect(0, 0, getWidth(), getHeight());
        for(Component comp : layers){
            comp.paint(grp);
        }
    }
    
    @Override
    public void update(Graphics grp){
        paint(grp);
    }
    
    @Override
    protected void addImpl(Component comp, Object constraints, int index){
        if(EventQueue.isDispatchThread()){
            if(comp instanceof RenderLayer){
                if(index>=0)
                    layers.insertElementAt((RenderLayer)comp, index);
                else
                    layers.add((RenderLayer)comp);
            }
            comp.setLocation(0, 0);
            comp.setSize(getSize());
            processContainerEvent(new ContainerEvent(this, ContainerEvent.COMPONENT_ADDED,comp));
            repaint();
        }
        else{
            invokeAndWait(new Runner(comp,constraints,index) {
                @Override public void r(Object[] args){addImpl((Component)args[0], args[1], (Integer)args[2]);}
            });
        }
    }
    
    @Override
    public Component[] getComponents(){
        return layers.toArray(new Component[0]);
    }
    
    @Override
    public int getComponentCount(){
        return layers.size();
    }
    
    @Override 
    public Component getComponent(int index){
        return layers.get(index);
    }
    
    @Override
    public void remove(Component comp){
        if(EventQueue.isDispatchThread()){
            layers.remove(comp);
        }
        else{
            invokeAndWait(new Runner(comp){
                @Override public void r(Object[] args){remove((Component)args[0]);}
            });
        }
    }
    
    @Override
    public void removeAll(){
        if(EventQueue.isDispatchThread()){
            for(Component comp : getComponents()){
                remove(comp);
            }
        }
        else{
            invokeAndWait(new Runnable(){
                @Override public void run(){removeAll();}
            });
        }
    }
    
    protected void invoke(Runnable r){
        EventQueue.invokeLater(r);
    }
    
    protected void invokeAndWait(Runnable r){
        try {
            EventQueue.invokeAndWait(r);
        } catch (InterruptedException ex) {
            log.log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }
}