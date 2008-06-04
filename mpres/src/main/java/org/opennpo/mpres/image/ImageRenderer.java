package org.opennpo.mpres.image;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;
import javax.swing.Renderer;
import org.opennpo.mpres.items.ImageScriptItem;

/**
 * A renderer for drawing ImageScriptItems.
 * @author Nate Jones
 */
public class ImageRenderer extends JComponent implements Renderer{
    private ImageScriptItem item;
    
    @Override
    public void setValue(Object aValue, boolean isSelected) {
        item = (ImageScriptItem)aValue;
    }

    @Override
    public Component getComponent() {
        return this;
    }
    
    @Override
    public void paint(Graphics grp){
        if(item!=null){
            Image img = (Image)item.getData();
            grp.drawImage(img, 0, 0, this);
        }
    }
}
