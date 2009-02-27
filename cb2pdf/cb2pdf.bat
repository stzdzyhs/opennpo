@echo off
set CBCLASSPATH=C:\Projects\opennpo\cb2pdf\target\cb2pdf-1.0-SNAPSHOT.jar;
set CBCLASSPATH=%CBCLASSPATH%;C:\Users\nate\.m2\repository\com\lowagie\itext\2.1.2\itext-2.1.2.jar
set CBCLASSPATH=%CBCLASSPATH%;C:\Users\nate\.m2\repository\commons-logging\commons-logging\1.1.1\commons-logging-1.1.1.jar

java -Xmx1024m -classpath "%CLASSPATH%;%CBCLASSPATH%" org.opennpo.cb2pdf.CB2PDF %1 %2