/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entity.Hobby;
import entity.Person;
import facade.Controller;
import java.util.ArrayList;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class JTest9001 {
    
    Controller cc = new Controller();
    
    public JTest9001() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    public void test1(){
        Person p1 = new Person("Marco", "Blum");
        p1.addHobby(new Hobby("whatever23456", "somtihng"));
        cc.addPerson(p1);
        
        
        
        Person p2 = new Person("David", "Blum");
        Hobby h1 = new Hobby("yeaaaassss23456s","asdfghjk");
        h1.addPersons(p1);
        cc.addHobby(h1);
        
        
        
    }
}
