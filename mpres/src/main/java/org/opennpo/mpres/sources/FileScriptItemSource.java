/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mpres.sources;

import org.opennpo.mpres.*;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author Nate Jones
 */
public class FileScriptItemSource extends JPanel implements ScriptItemSource {
    
    private Logger log = Logger.getLogger(FileScriptItemSource.class.getName());
    
    private Icon icon;
    private JFileChooser chooser;
    
    public FileScriptItemSource(){
        chooser = new JFileChooser();
        chooser.setControlButtonsAreShown(false);
        this.add(chooser);
        icon = Resources.getIcon("Folder.gif");
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public String getTitle() {
        return "File Browser";
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public ScriptItem getScriptItem(Component comp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasScriptItemAvailable(Component comp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
