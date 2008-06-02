/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mpres.items;

import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Renderer;
import org.opennpo.mpres.ScriptItem;
import org.opennpo.mpres.ScriptItemControl;

/**
 *
 * @author Nate Jones
 */
public class ImageScriptItem implements ScriptItem{
    private BufferedImage data;
    private String title;
    private Icon icon;
    
    public ImageScriptItem(String title, BufferedImage img){
        data = img;
        this.title = title;
        icon = new ImageIcon(img);
    }
    

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public Class<? extends Renderer> getRenderer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Class<? extends ScriptItemControl> getControl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
