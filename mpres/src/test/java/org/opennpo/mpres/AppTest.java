package org.opennpo.mpres;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.opennpo.mpres.image.ImageScriptItem;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws IntrospectionException
    {
        BeanInfo nfo = Introspector.getBeanInfo(ImageScriptItem.class);
        System.out.println(nfo.getBeanDescriptor().getDisplayName());
        for(PropertyDescriptor prop: nfo.getPropertyDescriptors()){
            System.out.println("\t"+prop.getDisplayName()+"("+prop.getName()+")");
        }
        
        assertTrue( true );
    }
}
