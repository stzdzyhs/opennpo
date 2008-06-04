package org.opennpo.beans;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author nate
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DisplayName {
    String value();
}
