package org.opennpo.mpres;

import java.awt.Component;
import javax.swing.Icon;

/**
 * Represents a source of ScriptItems.  
 * @author Nate Jones
 * @see ScriptItem
 */
public interface ScriptItemSource {
    /**
     * Gets an Icon for this Source.
     * May be null.
     * @return
     */
    Icon getIcon();
    
    /**
     * Gets a Human Readable name for this source.
     * Should never be null.
     * @return
     */
    String getTitle();
    
    /**
     * Gets the user interface for controling this source.
     * @return
     */
    Component getComponent();
    
    /**
     * Adds a listener to this source.
     * @param l
     */
    void addListener(ScriptItemListener l);
    
    /**
     * Removes a listener from this source.
     * @param l
     */
    void removeListener(ScriptItemListener l);
    
    /**
     * Suggests that the source move to the next ScriptItem.
     */
    void next();
}
