/*
 * FramePanel.java
 *
 * Created on May 20, 2008, 3:13 AM
 */

package org.opennpo.mpres.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import org.opennpo.Runner;
import org.opennpo.mpres.ScriptItem;
import org.opennpo.mpres.ScriptItemControl;
import org.opennpo.mpres.ScriptItemListener;
import org.opennpo.mpres.ScriptItemSource;

/**
 * The visual component for dealing with a particular frame.
 * @author  Nate Jones
 */
public class FramePanel extends javax.swing.JPanel implements ScriptItemListener{
    private Logger log = Logger.getLogger(FramePanel.class.getName());    
    private RenderPane renderPane;
    private JSeparator separator;
    private JPanel controlPanel;
    private Map<Class, ScriptItemControl> controls;
    
    
    /** Creates new form FramePanel */
    public FramePanel() {
        initComponents();
        controls = new HashMap<Class, ScriptItemControl>();
    }
    
    private void initComponents() {
        renderPane = new RenderPane();
        separator = new JSeparator();
        controlPanel = new JPanel();
        controlPanel.setLayout(null);
        add(renderPane);
        add(separator);
        add(controlPanel);
        controlPanel.setBackground(Color.DARK_GRAY);
        setLayout(null);
        resize();
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                resize();
            }
        });
        controlPanel.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                for(Component comp : controlPanel.getComponents()){
                    comp.setSize(controlPanel.getSize());
                }
            }
        });
    }
    
    private void resize(){
        renderPane.setLocation(5, 5);
        renderPane.setSize(getWidth()-10, (getHeight()/3)-10);
        separator.setSize((int)(getWidth()*0.75), 5);
        separator.setLocation((getWidth()-separator.getWidth())/2, renderPane.getHeight()+10);
        controlPanel.setLocation(5, separator.getY()+separator.getHeight());
        controlPanel.setSize(getWidth()-10, getHeight()-controlPanel.getY()-5);
    }

    @Override
    public void itemSelected(ScriptItemSource src, ScriptItem item) {
        log.info("Item Selected("+src.toString()+", "+item.toString()+")");
        if(EventQueue.isDispatchThread()){
            //renderPane.clear();
            renderPane.addLayer(item);
            controlPanel.removeAll();
            try {
                ScriptItemControl ctrl = null;
                if(controls.containsKey(item.getControl())){
                    ctrl = controls.get(item.getControl());
                }
                else{
                    ctrl = item.getControl().newInstance();
                }
                Component comp = ctrl.getScriptItemControl(item);
                controlPanel.add(comp);
                comp.setSize(controlPanel.getSize());
            } catch (InstantiationException ex) {
                Logger.getLogger(FramePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(FramePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            renderPane.invalidate();
            controlPanel.invalidate();
            renderPane.repaint();
            controlPanel.repaint();
        }
        else{
            EventQueue.invokeLater(new Runner(src, item){
                public void runImpl(Object[] args){
                    itemSelected((ScriptItemSource)args[0], (ScriptItem)args[1]);
                }
            });
        }
    }
}
