package org.opennpo.mpres;

import javax.swing.Icon;
import javax.swing.Renderer;

/**
 *
 * @author Nate Jones
 */
public interface ScriptItem {
    String getTitle();
    Icon getIcon();
    Object getData();
    Class<? extends Renderer> getRenderer();
    Class<? extends ScriptItemControl> getControl();
}
