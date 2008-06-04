/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mpres.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.Renderer;
import org.opennpo.mpres.ScriptItem;

/**
 *
 * @author Nate Jones
 */
public class RenderLayer extends JComponent{
    private ScriptItem item;
    private Map<Class, Renderer> renderers;
    public RenderLayer(){
        item = null;
        renderers = new HashMap<Class, Renderer>();
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
                    add(renderer.getComponent());
                } catch (InstantiationException ex) {
                    Logger.getLogger(RenderLayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(RenderLayer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }
    
    public ScriptItem getScriptItem(){
        return item;
    }
}
