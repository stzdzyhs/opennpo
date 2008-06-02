/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mpres.sources;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import org.opennpo.mpres.*;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.Icon;
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
    private List<ScriptItemListener> siListeners;
    private List<FileScriptItemFactory> factories;
    
    public FileScriptItemSource(){
        siListeners = Collections.synchronizedList(new Vector<ScriptItemListener>());
        this.setLayout(new BorderLayout());
        factories = new Vector<FileScriptItemFactory>();
        StringBuilder buf = new StringBuilder("FileScriptItemFactory:\n");
        for(FileScriptItemFactory fac: ServiceLoader.load(FileScriptItemFactory.class)){
            factories.add(fac);
            buf.append("\t"+fac.getClass().getName()+"\n");
        }
        log.info(buf.toString());
        chooser = new JFileChooser();
        chooser.setControlButtonsAreShown(false);
        this.add(chooser);
        icon = Resources.getIcon("Folder.gif");
        chooser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = chooser.getSelectedFile();
                FileScriptItemFactory fac = getFactory(f);
                if(fac != null){
                    ScriptItem item = fac.getItem(f);
                    for(ScriptItemListener l : siListeners){
                        l.itemSelected(FileScriptItemSource.this, item);
                    }
                }
                else{
                    log.fine("No Handler could be found for "+f);
                }
            }
        });
    }
    
    protected FileScriptItemFactory getFactory(File f){
        for(FileScriptItemFactory fac : factories){
            if(fac.accept(f))
                return fac;
        }
        return null;
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
    public void addListener(ScriptItemListener l) {
        siListeners.add(l);
    }

    @Override
    public void removeListener(ScriptItemListener l) {
        siListeners.remove(l);
    }
    
    @Override
    public void next(){
        //Should go to the next file?  What would that mean?
    }
}
