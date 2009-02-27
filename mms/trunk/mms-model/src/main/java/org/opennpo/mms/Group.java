package org.opennpo.mms;

import java.util.List;

/**
 *
 * @author Nate Jones
 */
public abstract class Group implements Entity {
    
    public abstract List<Entity> getMembers();

    @Override
    public Type getType() {
        return Type.Group;
    }
    
}
