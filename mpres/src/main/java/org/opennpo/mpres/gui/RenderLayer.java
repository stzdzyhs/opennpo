/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mpres.gui;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Renderer;
import org.opennpo.mpres.ScriptItem;

/**
 *
 * @author Nate Jones
 */
public class RenderLayer extends JPanel{
    private ScriptItem item;
    private Map<Class, Renderer> renderers;
    Component child;
    public RenderLayer(){
        item = null;
        renderers = new HashMap<Class, Renderer>();
        setLayout(null);
        child = null;
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                if(child!=null){
                    child.setSize(getSize());
                }
            }
        });
    }
    
    public void setScriptItem(ScriptItem item){
        this.removeAll();
        this.item = item;
        if(item!=null){
            Renderer renderer = renderers.get(item.getRenderer());
            if(renderer == null){
                try {
                    renderer = item.getRenderer().newInstance();
                    renderer.setValue(item, true);
                    child = renderer.getComponent();
                    add(child);
                    child.setSize(getSize());
                } catch (InstantiationException ex) {
                    Logger.getLogger(RenderLayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(RenderLayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        repaint();
    }
    
    public ScriptItem getScriptItem(){
        return item;
    }
}
