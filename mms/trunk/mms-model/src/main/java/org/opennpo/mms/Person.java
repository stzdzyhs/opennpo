package org.opennpo.mms;

/**
 *
 * @author Nate Jones
 */
public abstract class Person implements Entity {

    @Override
    public Type getType() {
        return Type.Person;
    }

}
