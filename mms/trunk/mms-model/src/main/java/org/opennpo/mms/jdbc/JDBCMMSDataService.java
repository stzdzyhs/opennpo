/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mms.jdbc;

import java.util.List;
import javax.sql.DataSource;
import org.opennpo.mms.Entity;
import org.opennpo.mms.Group;
import org.opennpo.mms.MMSDataService;
import org.opennpo.mms.Person;

/**
 *
 * @author Nate Jones
 */
public class JDBCMMSDataService implements MMSDataService{
    private DataSource src;
    
    public JDBCMMSDataService(DataSource src){
        if(src==null) 
            throw new IllegalArgumentException("The DataSource provided to JDBCMMSDataService may not be null.");
        this.src = src;
    }

    @Override
    public void updateEntity(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entity selectEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Person createPerson() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Group createGroup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Person> getAllPeople() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Group> getAllGroups() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Entity> selectEntities(String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
