/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo;

/**
 *
 * @author Nate Jones
 */
public abstract class Runner implements Runnable{
    private Object[] args;
    public Runner(Object... args){
        this.args = args;
    }
    
    @Override
    public void run(){
        runImpl(args);
    }
    
    public abstract void runImpl(Object[] args);
}
