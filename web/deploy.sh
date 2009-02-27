#!/bin/bash
FROMDIR=.
DESTDIR=ftp://n8j1s12345@opennpo.org
rsync -i -v -a -f"- .svn" -f"- wp-config.php" -f"- .htaccess" $FROMDIR $DESTDIR
