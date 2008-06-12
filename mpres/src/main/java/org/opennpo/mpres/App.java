package org.opennpo.mpres;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.opennpo.mpres.gui.MainFrame;
import javax.swing.UIManager;
import org.opennpo.conf.Configuration;

/**
 * Main Application Class for MPres.
 * @author Nate Jones
 */
public class App 
{
    private static Logger log = Logger.getLogger(App.class.getName());
    private static MainFrame frame;
    
    /**
     * Main method for the MPres application.
     * Calls Conf.startUp() to load configuration data.
     * Sets up log handlers. 
     * Starts visual environment.
     * @param args
     * @throws java.lang.Exception
     */
    public static void main( String[] args   ) throws Exception
    {
        Conf.startUp();
        setupLogs();
        log.fine(getConfiguration());
        String lnf = Conf.get(Conf.LNF_KEY, UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(lnf);
        log.fine("LookAndFeel: "+lnf);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new MainFrame();
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
    
    private static void setupLogs(){
        try {
            Logger plog = Logger.getLogger("org.opennpo");
            plog.setLevel(Level.ALL);
            plog.addHandler(new Handler() {

                @Override
                public void publish(LogRecord record) {
                    if ((record.getLevel() == Level.INFO) && (frame != null)) {
                        frame.setStatus(record.getMessage());
                    } else if (record.getLevel() == Level.SEVERE) {
                        JOptionPane.showMessageDialog(frame, record, "Severe Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                @Override
                public void flush() {
                }

                @Override
                public void close() throws SecurityException {
                }
            });
            FileHandler fhan = new FileHandler("%t/MPresLog%u_%g.xml", 0, 10);
            plog.addHandler(fhan);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            System.err.flush();
        } 
    }
    
    private static String getConfiguration(){
        try {
            StringWriter writer = new StringWriter();
            PrintWriter out = new PrintWriter(writer);
            out.println();
            out.println("# System Properties:");
            System.getProperties().store(out, null);
            out.println();
            out.println("# OpenNPO Properties: ");
            for(Configuration.Entry entry : Conf.getConfig().entrySet()){
                out.println(entry.getKey()+"="+entry.getValue());
            }
            return writer.toString();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
