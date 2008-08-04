/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mpres.image;

import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Renderer;
import org.opennpo.beans.*;
import org.opennpo.beans.EnumPropertyEditor;
import org.opennpo.mpres.ScriptItem;
import org.opennpo.mpres.ScriptItemControl;

/**
 *
 * @author Nate Jones
 */
@DisplayName("Image Script Item")
public class ImageScriptItem implements ScriptItem{
    private BufferedImage data;
    private String title;
    private Icon icon;
    private StretchStyle stretchStyle;
    
    public ImageScriptItem(String title, BufferedImage img){
        data = img;
        this.title = title;
        icon = new ImageIcon(img);
        stretchStyle = StretchStyle.StretchMaintainRatio;
    }

    @Override
    @DisplayName("Title")
    public String getTitle() {
        return title;
    }

    @Override
    @Expert
    public Icon getIcon() {
        return icon;
    }
    
    @Override
    @Expert
    public Object getData() {
        return data;
    }

    @Override
    @Expert
    public Class<? extends Renderer> getRenderer() {
        return ImageRenderer.class;
    }

    @Override
    @Expert
    public Class<? extends ScriptItemControl> getControl() {
        return ImageControl.class;
    }
    
    @Override
    public String toString(){
        return getClass().getName()+"["+getTitle()+"]";
    }
    
    @DisplayName("Stretch Style")
    public StretchStyle getStretchStyle(){
        return stretchStyle;
    }
    
    public void setStretchStyle(StretchStyle ss){
        stretchStyle = ss;
    }
    
    public static class StretchStylePropertyEditor extends EnumPropertyEditor{
        public StretchStylePropertyEditor(){
            super(StretchStyle.class);
        }
    }
    
    @PropertyEditorAnnotation(StretchStylePropertyEditor.class)
    public static enum StretchStyle{
        StretchMaintainRatio, 
        StretchFullScreen
    }
}
