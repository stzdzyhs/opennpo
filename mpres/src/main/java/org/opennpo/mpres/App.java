package org.opennpo.mpres;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import org.opennpo.mpres.gui.MainFrame;
import javax.swing.UIManager;

/**
 * 
 * 
 */
public class App 
{
    private static Logger log = Logger.getLogger(App.class.getName());
    
    public static void main( String[] args   ) throws Exception
    {
        Conf.startUp();
        String lnf = Conf.get(Conf.LNF_KEY, UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(lnf);
        log.info("LookAndFeel: "+lnf);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        Conf.shutDown();
                    }
                });
                frame.setVisible(true);
            }
        });
    }
}
