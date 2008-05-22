package org.opennpo.mpres;

import java.awt.Component;
import javax.swing.Icon;

/**
 *
 * @author nate
 */
public interface ScriptItemSource {
    Icon getIcon();
    String getTitle();
    Component getComponent();
    ScriptItem getScriptItem(Component comp);
    boolean hasScriptItemAvailable(Component comp);
}
