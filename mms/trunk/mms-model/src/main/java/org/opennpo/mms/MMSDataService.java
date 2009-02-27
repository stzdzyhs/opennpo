package org.opennpo.mms;

import java.util.List;

/**
 * 
 * @author Nate Jones
 */
public interface MMSDataService {
    
    /**
     * 
     * @param entity
     */
    void updateEntity(Entity entity);
    
    /**
     * 
     * @param id
     * @return
     */
    Entity selectEntity(int id);
    
    /**
     * 
     * @return
     */
    Person createPerson();
    
    /**
     * 
     * @return
     */
    Group createGroup();
    
    /**
     * 
     * @param id
     * @return
     */
    boolean deleteEntity(int id);
    
    /**
     * 
     * @return
     */
    List<Person> getAllPeople();
    
    /**
     * 
     * @return
     */
    List<Group> getAllGroups();
    
    /**
     * 
     * @param query
     * @return
     */
    List<Entity> selectEntities(String query);
    
}
