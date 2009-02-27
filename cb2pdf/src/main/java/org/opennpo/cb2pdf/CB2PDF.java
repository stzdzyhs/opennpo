package org.opennpo.cb2pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

/**
 * A Command to convert a CBR/CBZ to a PDF
 */
public class CB2PDF 
{
    private static final Logger log = Logger.getLogger(CB2PDF.class.getName());
    
    public static void convert(File archive, File dest) throws IOException, RarException, DocumentException{
        log.info("Start "+archive.getName()+"...");
        String f = archive.getName().toUpperCase();
        Iterable<Image> images;
        if(f.endsWith("CBZ")||f.endsWith("ZIP")){
            log.info("Extracting images from ZIP...");
            images = getImagesFromZip(archive);
        }
        else if(f.endsWith("CBR")||f.endsWith("CBZ")){
            log.info("Extracting images from RAR...");
            images = getImagesFromRar(archive);
        }
        else if(archive.isDirectory()){
            log.info("Getting images from Folder...");
            images = getImagesFromFolder(archive);
        }
        else{
            log.severe(archive.getName()+" is neither a zip nor a rar.");
            return;
        }
        log.info("Done extacting images.");
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(dest));
        log.info("Writing PDF...");
        //doc.setPageSize(new Rectangle(first.getWidth(null),first.getHeight(null)));
        doc.setMargins(0.0f, 0.0f, 0.0f, 0.0f);
        doc.open();
        for(Image img : images){
            if(img!=null){
                doc.setPageSize(new Rectangle(img.getWidth(null),img.getHeight(null)));
                doc.newPage();
                com.lowagie.text.Image ele = com.lowagie.text.Image.getInstance(writer, img, 0.9f);
                doc.add(ele);
            }
        }
        doc.close();
        log.info("Done writing PDF.");
        log.info("Done "+archive.getName()+".");
    }
    
    protected static Iterable<Image> getImagesFromFolder(File f){
        SortedSet<String> files = new TreeSet<String>();
        for(String fname : f.list()){
            if(isImage(fname)){
                files.add(fname);
            }
        }
        return new ImageableIterable(f, files, new Imageable(){
            @Override
            public Image getImage(Object arch, Object entry) {
                try {
                    return ImageIO.read(new File((File) arch, (String) entry));
                } catch (IOException ex) {
                    Logger.getLogger(CB2PDF.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            } 
        });
    }
    
    protected static List<Image> getImagesFromZip(File f) throws ZipException, IOException{
        Vector<Image> images = new Vector<Image>();
        if(f.canRead()){
            ZipFile zip = new ZipFile(f);
            Enumeration<? extends ZipEntry> en = zip.entries();
            TreeMap<String, ZipEntry> entries = new TreeMap<String, ZipEntry>();
            while(en.hasMoreElements()){
                ZipEntry entry = en.nextElement();
                entries.put(entry.getName(), entry);
            }
            for(ZipEntry entry : entries.values()){
                Image img = ImageIO.read(zip.getInputStream(entry));
                if(img!=null){
                    images.add(img);
                }
                else{
                    log.warning("Could not create image from: "+entry.getName());                    
                }
            }
        }
        return images;
    }
    
    protected static List<Image> getImagesFromRar(File f) throws IOException, RarException{
        Vector<Image> images = new Vector<Image>();
        if(f.canRead()){
            Archive ach = new Archive(f);
            TreeMap<String, FileHeader> files = new TreeMap<String, FileHeader>();
            for(FileHeader header: ach.getFileHeaders()){
                files.put(header.getFileNameString(), header);
            }
            for(Map.Entry<String, FileHeader> entry : files.entrySet()){
                FileHeader header = entry.getValue();
                if(isImage(header.getFileNameString())){
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    ach.extractFile(header, stream);
                    Image image = ImageIO.read(new ByteArrayInputStream(stream.toByteArray()));
                    if(image!=null){
                        images.add(image);
                    }
                    else{
                        log.warning("Could not create image from: "+header.getFileNameString());
                    }
                    stream = null;
                    image = null;
                }
                else{
                    log.info(header.getFileNameString()+" found, but not an image so skipped.");
                }
            }
        }
        return images;
    }
    
    public static void main( String[] args ) throws Exception{
        if(args.length>0){
            File input = new File(args[0]);
            File output;
            if(args.length>1){
                output = new File(args[1]);
            }
            else{
                output = new File(args[0]+".pdf");
            }
            convert(input, output);
        }
        else{
            System.err.println("Usage CB2PDF <input file> [<output file>]");
        }
    }
    
    public static boolean isImage(String fname){
        for(String suffix : ImageIO.getReaderFileSuffixes()){
            if(fname.toUpperCase().endsWith(suffix.toUpperCase()))
                return true;
        }
        return false;
    }
    
    public static interface Imageable{
        Image getImage(Object arch, Object entry);
    }
    
    public static class ImageableIterable implements Iterable<Image>{
        private Object data;
        private Iterable entries;
        private Imageable img;
        
        public ImageableIterable(Object data, Iterable entries, Imageable img){
            this.data = data;
            this.entries = entries;
            this.img = img;
        }

        @Override
        public Iterator<Image> iterator() {
            return new Iterator<Image>(){
                Iterator iter;

                @Override
                public boolean hasNext() {
                    if(iter==null) iter = entries.iterator();
                    return iter.hasNext();
                }

                @Override
                public Image next() {
                    if(iter==null) iter = entries.iterator();
                    return img.getImage(data, iter.next());
                }

                @Override
                public void remove() {
                }
                
            };
        }
        
    }
}
