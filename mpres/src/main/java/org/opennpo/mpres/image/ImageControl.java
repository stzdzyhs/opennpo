package org.opennpo.mpres.image;

import java.awt.Component;
import org.opennpo.gui.PropertyGrid;
import org.opennpo.mpres.ScriptItem;
import org.opennpo.mpres.ScriptItemControl;

/**
 *
 * @author Nate Jones
 */
public class ImageControl implements ScriptItemControl{
    private PropertyGrid grid = new PropertyGrid();
    
    @Override
    public Component getScriptItemControl(ScriptItem item) {
        grid.setSource(item);
        return grid;
    }

}
