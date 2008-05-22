package org.opennpo.mpres;

import javax.swing.Icon;

/**
 *
 * @author Nate Jones
 */
public interface ScriptItem {
    String getTitle();
    Icon getIcon();
    Object getData();
    Class<? extends ScriptItemRenderer> getRenderer();
    Class<? extends ScriptItemControl> getControl();
    ScriptItemSource getSource();
}
