/*
 * MainFrame.java
 *
 * Created on May 20, 2008, 2:23 AM
 */

package org.opennpo.mpres.gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import org.opennpo.conf.Configuration;
import org.opennpo.conf.ConfigurationManager;

/**
 * This is the main window for the MPres application.
 * @author  Nate Jones
 */
public class MainFrame extends JFrame {
    // <editor-fold defaultstate="collapsed" desc="Configuration Values">
    private static final String WidthOp = "width";
    private static final String HeightOp = "height";
    private static final String XOp = "x";
    private static final String YOp = "y";
    private static final String MainSplitterDivOp = "mainSplitterDiv";
    private static final String SrcSplitterDivOp = "srcSplitterDivOp";
    //</editor-fold>
    
    private Configuration conf;
    private JMenuBar menuBar;
    private JSplitPane mainSplitter;
    private JSplitPane presSplitter;
    private FramePanel deckDetail;
    private FramePanel liveDetail;
    private JSplitPane srcSplitter;
    private ScriptPanel scriptEditorPanel;
    private DataSourcePanel dataSourcePanel;
    
    /** 
     * Creates new form MainFrame 
     */
    public MainFrame() {
        conf = ConfigurationManager.getAppConfig().getClassSubset(MainFrame.class);
        initVisualComponents();
        initDataFlow();
    }
    
    private void saveLayout(){
        conf.put(WidthOp, getWidth());
        conf.put(HeightOp, getHeight());
        conf.put(XOp, getX());
        conf.put(YOp, getY());
        conf.put(MainSplitterDivOp, mainSplitter.getDividerLocation());
        conf.put(SrcSplitterDivOp, srcSplitter.getDividerLocation());
    }
    
    private void initDataFlow(){
        
    }
    
    private void initVisualComponents(){
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
        deckDetail = new FramePanel();
        liveDetail = new FramePanel();
        presSplitter.setLeftComponent(deckDetail);
        presSplitter.setRightComponent(liveDetail);
        srcSplitter = new JSplitPane();
        srcSplitter.setResizeWeight(0.5);
        srcSplitter.setOrientation(JSplitPane.VERTICAL_SPLIT);
        scriptEditorPanel = new ScriptPanel();
        dataSourcePanel = new DataSourcePanel();
        srcSplitter.setTopComponent(scriptEditorPanel);
        srcSplitter.setBottomComponent(dataSourcePanel);
        mainSplitter.setRightComponent(presSplitter);
        mainSplitter.setLeftComponent(srcSplitter);
        getContentPane().add(mainSplitter);
        pack();
        setSize(conf.get(WidthOp, 800),conf.get(WidthOp, 600));
        setLocation(conf.get(XOp, 0), conf.get(YOp, 0));
        mainSplitter.setDividerLocation(conf.get(MainSplitterDivOp, 200));
        srcSplitter.setDividerLocation(conf.get(SrcSplitterDivOp, 400));
        addComponentListener(new ComponentAdapter(){
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
