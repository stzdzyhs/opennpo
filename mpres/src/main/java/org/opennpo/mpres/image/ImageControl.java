/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mpres.image;

import java.awt.Component;
import java.beans.PropertyEditorSupport;
import org.opennpo.mpres.ScriptItem;
import org.opennpo.mpres.ScriptItemControl;

/**
 *
 * @author Nate Jones
 */
public class ImageControl implements ScriptItemControl{
    PropertyEditorSupport support = new PropertyEditorSupport();
    @Override
    public Component getScriptItemControl(ScriptItem item) {
        support.setSource(item);
        return support.getCustomEditor();
    }

}
