/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.main.controller;

/**
 *
 * @author buthe_s
 */
public final class FactoryClass {
    private static Controller controller;
    private static int count = 0;
    
    public FactoryClass() throws InterruptedException{
        controller = new Controller();
        count++;
    }
   
    
    public static Controller getController() throws InterruptedException{
        
        if (count == 0){
            controller = new Controller();
            count++;
        }
        return controller;
    }
}
