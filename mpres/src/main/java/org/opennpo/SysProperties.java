package org.opennpo;

/**
 * This is a convenience class for accessing the default System properties.
 * 
 * Default System Properties:
 * java.version  	Java Runtime Environment version
 * java.vendor          Java Runtime Environment vendor
 * java.vendor.url 	Java vendor URL
 * java.home            Java installation directory
 * java.vm.specification.version 	Java Virtual Machine specification version
 * java.vm.specification.vendor 	Java Virtual Machine specification vendor
 * java.vm.specification.name 	Java Virtual Machine specification name
 * java.vm.version 	Java Virtual Machine implementation version
 * java.vm.vendor 	Java Virtual Machine implementation vendor
 * java.vm.name 	Java Virtual Machine implementation name
 * java.specification.version 	Java Runtime Environment specification version
 * java.specification.vendor 	Java Runtime Environment specification vendor
 * java.specification.name 	Java Runtime Environment specification name
 * java.class.version 	Java class format version number
 * java.class.path 	Java class path
 * java.library.path 	List of paths to search when loading libraries
 * java.io.tmpdir 	Default temp file path
 * java.compiler 	Name of JIT compiler to use
 * java.ext.dirs 	Path of extension directory or directories
 * os.name 	Operating system name
 * os.arch 	Operating system architecture
 * os.version 	Operating system version
 * file.separator 	File separator ("/" on UNIX)
 * path.separator 	Path separator (":" on UNIX)
 * line.separator 	Line separator ("\n" on UNIX)
 * user.name 	User's account name
 * user.home 	User's home directory
 * user.dir 	User's current working directory
 * @author Nate Jones
 */
public final class SysProperties {
    public static String getJavaVersion(){
        return System.getProperty("java.version");
    }
    
    public static String getJavaVendor(){
        return System.getProperty("java.vendor");
    }
    
    public static String getJavaVendorUrl(){
        return System.getProperty("java.vendor.url");
    }
    
    public static String getJavaHome(){
        return System.getProperty("java.home");
    }
    
    public static String getJavaVMSpecificationVersion(){
        return System.getProperty("java.vm.specification.version");
    }
    
    public static String getJavaVMSpecificationVendor(){
        return System.getProperty("java.vm.specification.vendor");
    }
    
    public static String getJavaVMSpecificationName(){
        return System.getProperty("java.vm.specification.name");
    }
    
    public static String getJavaVMVersion(){
        return System.getProperty("java.vm.version");
    }
    
    public static String getJavaVMVendor(){
        return System.getProperty("java.vm.vendor");
    }
    
    public static String getJavaVMName(){
        return System.getProperty("java.vm.name");
    }
    
    public static String getJavaSpecificationVersion(){
        return System.getProperty("java.specification.version");
    }
    
    public static String getJavaSpecificationVendor(){
        return System.getProperty("java.specification.vendor");
    }
    
    public static String getJavaSpecificationName(){
        return System.getProperty("java.specification.name");
    }
    
    public static String getJavaClassVersion(){
        return System.getProperty("java.class.version");
    }
    
    public static String getJavaClassPath(){
        return System.getProperty("java.class.path");
    }
    
    public static String getJavaLibraryPath(){
        return System.getProperty("java.library.path");
    }
    
    public static String getJavaIOTmpDir(){
        return System.getProperty("java.io.tmpdir");
    }
}
