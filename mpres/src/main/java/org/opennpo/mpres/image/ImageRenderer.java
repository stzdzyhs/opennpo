package org.opennpo.mpres.image;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Logger;
import javax.swing.Renderer;

/**
 * A renderer for drawing ImageScriptItems.
 * @author Nate Jones
 */
public class ImageRenderer extends Component implements Renderer{
    private static final Logger log = Logger.getLogger(ImageRenderer.class.getName());
    private ImageScriptItem item;
    private Image img;
    private int wscaled = 0;
    private int hscaled = 0;
    
    public ImageRenderer(){
        
    }
    
    @Override
    public void setValue(Object aValue, boolean isSelected) {
        item = (ImageScriptItem)aValue;
        if(item!=null){
            img = (Image)item.getData();
            setScale();
        }
    }
    
    private void setScale(){
        if(img==null) return;
        int w = img.getWidth(this);
        int h = img.getHeight(this);
        if((w>getWidth())||(h>getHeight())){
            double r = (double)h/(double)w;
            if((w-getWidth())>(h-getHeight())){
                wscaled = getWidth();
                hscaled = (int)(wscaled*r);
            }
            else{
                hscaled = getHeight();
                wscaled = (int)(hscaled/r);
            }
        }
        else{
            wscaled = w;
            hscaled = h;
        }
    }
    
    @Override
    public Component getComponent() {
        return this;
    }
    
    @Override
    public void paint(Graphics grp){
        if(img!=null){
            grp.drawImage(img, 0, 0, wscaled, hscaled, null);
        }
        else{
            log.info("Null Image!!");
        }
    }
    
    @Override
    public void setSize(Dimension d){
        super.setSize(d);
        setScale();
    }
}
