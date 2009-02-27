/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.mms.jdbc;

import java.beans.BeanInfo;
import org.opennpo.mms.MMSDataService;
import org.opennpo.mms.MMSDataServiceFactory;

/**
 *
 * @author Nate Jones
 */
public class JDBCMMSDataServiceFactory extends MMSDataServiceFactory{

    @Override
    public MMSDataService createService(Object configurationBean) {
        return null;
    }

    @Override
    public String getName() {
        return "JDBC";
    }

    @Override
    public BeanInfo getConfigurationBeanInfo() {
        return null;
    }

}
