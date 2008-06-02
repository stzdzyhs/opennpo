package org.opennpo.mpres;

import javax.swing.Icon;
import javax.swing.Renderer;

/**
 * This is the interface for all media items handled by mpres.  It could 
 * represent an image or a document or many other things.  The important thing 
 * is that it tells the framework how to render itself and how to control it.
 * @author Nate Jones
 */
public interface ScriptItem {
    String getTitle();
    Icon getIcon();
    Object getData();
    Class<? extends Renderer> getRenderer();
    Class<? extends ScriptItemControl> getControl();
}
