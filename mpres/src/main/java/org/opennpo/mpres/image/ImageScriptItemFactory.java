package org.opennpo.mpres.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.opennpo.mpres.FileScriptItemFactory;
import org.opennpo.mpres.ScriptItem;

/**
 *
 * @author Nate Jones
 */
public class ImageScriptItemFactory implements FileScriptItemFactory{
    private Logger log = Logger.getLogger(ImageScriptItemFactory.class.getName());
    FileNameMap fmap;
    
    public ImageScriptItemFactory(){
        org.opennpo.beans.Utility.setupBeanInfo(ImageScriptItem.class);
        fmap = URLConnection.getFileNameMap();
    }

    @Override
    public boolean accept(URL f) {
        String type = fmap.getContentTypeFor(f.getFile());
        return (type!=null)&&(type.startsWith("image/"));
    }

    @Override
    public ScriptItem getItem(URL f) {
        ImageScriptItem itm = null;
        try {
            BufferedImage img = ImageIO.read(f);
            itm = new ImageScriptItem(f.getFile(), img);
            log.info("Loaded: "+f);
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return itm;
    }
}
