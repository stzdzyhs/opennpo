package org.opennpo.conf;

import java.io.OutputStream;

/**
 *
 * @author Nate Jones
 */
public interface ConfigurationWriter {
    void write(Configuration conf, OutputStream out);
}
