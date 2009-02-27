package org.opennpo.gui;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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

    JFrame frame = new JFrame();
    
    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        frame.setContentPane(new WebComponent());
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        assertTrue( true );
    }
}
