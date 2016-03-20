/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import javax.persistence.Persistence;

/**
 *
 * @author David
 */
public class Tester {
    
    
    public static void main(String[] args) {
        
//        TestData td = new TestData();
//        td.CreateUsers();
        Persistence.generateSchema("PU", null);

    }
    
    
}
