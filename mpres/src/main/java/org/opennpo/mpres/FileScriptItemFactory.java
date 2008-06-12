package org.opennpo.mpres;

import java.net.URL;

/**
 *
 * @author Nate Jones
 */
public interface FileScriptItemFactory {
    boolean accept(URL f);
    ScriptItem getItem(URL f);
}
