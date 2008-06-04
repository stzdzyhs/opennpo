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
import org.opennpo.mpres.items.ImageScriptItem;

/**
 *
 * @author Nate Jones
 */
public class ImageScriptItemFactory implements FileScriptItemFactory{
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
        } catch (IOException ex) {
            Logger.getLogger(ImageScriptItemFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itm;
    }
}
