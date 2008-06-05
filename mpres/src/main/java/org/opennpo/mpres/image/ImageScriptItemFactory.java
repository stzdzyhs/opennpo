package org.opennpo.mpres.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
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
        fmap = URLConnection.getFileNameMap();
    }

    @Override
    public boolean accept(File f) {
        String type = fmap.getContentTypeFor(f.getName());
        return (type!=null)&&(type.startsWith("image/"));
    }

    @Override
    public ScriptItem getItem(File f) {
        ImageScriptItem itm = null;
        try {
            BufferedImage img = ImageIO.read(f);
            itm = new ImageScriptItem(f.getName(), img);
            log.info("ImageScriptItem created from "+f.getName());
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return itm;
    }
}
