package org.opennpo.mms;

/**
 * The base class for the data objects in opennpo mms.
 * @author Nate Jones
 */
public interface Entity {
    
    public String getName();
    
    public int getId();
    
    public ContactInfo getContactInfo();
    
    public enum Type{
        Person(1),
        Group(2);
        
        private int id;
        Type(int id){
            this.id = id;
        }
        
        public int getId(){
            return this.id;
        }
    }
    
    public abstract Type getType();
}
