package org.opennpo.gui;


import javax.swing.JComponent;
import javax.swing.JTextPane;

/**
 *
 * @author Nate Jones
 */
public class WebComponent extends JComponent{
    private JTextPane textPane;
    public WebComponent(){
        initComponent();
    }
    
    protected void initComponent(){
        textPane = new JTextPane();
        add(textPane);
    }
}
