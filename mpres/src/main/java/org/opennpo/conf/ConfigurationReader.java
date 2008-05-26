package org.opennpo.conf;

import java.io.InputStream;

/**
 *
 * @author Nate Jones
 */
public interface ConfigurationReader {
    void read(Configuration conf, InputStream input);
}
