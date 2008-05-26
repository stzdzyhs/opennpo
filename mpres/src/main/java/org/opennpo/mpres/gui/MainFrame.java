/*
 * MainFrame.java
 *
 * Created on May 20, 2008, 2:23 AM
 */

package org.opennpo.mpres.gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import org.opennpo.conf.Configuration;
import org.opennpo.mpres.Conf;

/**
 * This is the main window for the MPres application.
 * @author  Nate Jones
 */
public class MainFrame extends javax.swing.JFrame {
    
    private JMenuBar menuBar;
    private JSplitPane mainSplitter;
    private JSplitPane presSplitter;
    private DetailPanel deckDetail;
    private DetailPanel liveDetail;
    private JSplitPane srcSplitter;
    private ScriptEditorPanel scriptEditorPanel;
    private DataSourcePanel dataSourcePanel;
    
    /** 
     * Creates new form MainFrame 
     */
    public MainFrame() {
        //initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MPres");
        getContentPane().setLayout(new java.awt.GridLayout());
        menuBar = new JMenuBar();
        JMenu menu = null;
        menu = new JMenu("File");
        menuBar.add(menu);
        setJMenuBar(menuBar);
        mainSplitter = new JSplitPane();
        mainSplitter.setResizeWeight(0.5);
        presSplitter = new JSplitPane();
        presSplitter.setResizeWeight(0.5);
        deckDetail = new DetailPanel();
        liveDetail = new DetailPanel();
        presSplitter.setLeftComponent(deckDetail);
        presSplitter.setRightComponent(liveDetail);
        srcSplitter = new JSplitPane();
        srcSplitter.setResizeWeight(0.5);
        srcSplitter.setOrientation(JSplitPane.VERTICAL_SPLIT);
        scriptEditorPanel = new ScriptEditorPanel();
        dataSourcePanel = new DataSourcePanel();
        srcSplitter.setTopComponent(scriptEditorPanel);
        srcSplitter.setBottomComponent(dataSourcePanel);
        mainSplitter.setRightComponent(presSplitter);
        mainSplitter.setLeftComponent(srcSplitter);
        getContentPane().add(mainSplitter);
        pack();
        Configuration conf = Conf.getConfig();
        setSize(conf.get("MainFrame.width", 800),conf.get("MainFrame.height", 600));
        this.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                saveLayout();
            }
            @Override
            public void componentMoved(ComponentEvent e){
                saveLayout();
            }
        });
    }
    
    private void saveLayout(){
        Configuration conf = Conf.getConfig();
        conf.put("MainFrame.width", getWidth());
        conf.put("MainFrame.height", getHeight());
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MPres");
        getContentPane().setLayout(new java.awt.GridLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
