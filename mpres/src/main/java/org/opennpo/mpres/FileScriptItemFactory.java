/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mpres;

import java.io.File;

/**
 *
 * @author nate
 */
public interface FileScriptItemFactory {
    boolean accept(File f);
    ScriptItem getItem(File f);
}
