/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for writing Runnable or Callable classes that require arguments
 * in shorthand.  A typical use of this class would be to call a handler 
 * function. 
 * <pre><code>
 *  protected void addImpl(Component comp, Object constraints, int index){
 *      if(EventQueue.isDispatchThread()){
 *          //...Implement Component Addition...
 *      }
 *      else{
 *          EventQueue.invokeAndWait(new Runner(comp, constraints, index) {
 *              public void r(Object[] args){ addImpl((Component)args[0], args[1], (Integer)args[2]); }
 *          });
 *      }
 *  }
 * </code></pre>
 * @author Nate Jones
 * @version 1.0
 */
public class Runner<T> implements Runnable, Callable<T>{
    private Object[] args;
    private T result;
    
    /**
     * Creates a new instance of Runner using the specified args.
     * @param args
     */
    public Runner(Object... args){
        this.args = args;
        result = null;
    }
    
    @Override
    public void run(){
        try {
            r(args);
            result = c(args);
        } catch (Exception ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override 
    public T call(){
        try {
            r(args);
            result = c(args);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Returns the result of calling c.  Will be null unless c is overriden.
     * @return
     */
    public T getResult(){
        return result;
    }
    
    /**
     * This method should be overriden if this runner is calling a method with 
     * no return value.
     * @param args The args passed to the constructor of this Runner.
     * @throws java.lang.Exception
     */
    public void r(Object[] args) throws Exception{
        
    }
    
    /**
     * This method should be overriden if this runner is calling a method with a 
     * return value.
     * @param args
     * @return
     * @throws java.lang.Exception
     */
    public T c(Object[] args) throws Exception{
        return null;
    }
}
