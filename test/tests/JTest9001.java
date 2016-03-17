/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.Controller;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author David
 */
public class JTest9001 {

    

    public JTest9001() {
    }

    @BeforeClass
    public static void setUpClass() {
        Persistence.generateSchema("PU", null);
        Controller.addHobby(new Hobby("Running", "use energy to move legs to gain velocity"));
        Controller.addHobby(new Hobby("Biking", "use energy to move legs to gain velocity"));
        Controller.addHobby(new Hobby("Gaming", "use energy to move fingers and wrists to move mouse"));
        Controller.addHobby(new Hobby("Masturbating", "use energy to move wrist and tighten grip to make friction"));
        

        Controller.addPerson(new Person("p1", "Blum"));
        Controller.addPerson(new Person("p2", "Blum"));
        Controller.addPerson(new Person("p3", "Blum"));
        
        Controller.addCompany(new Company("el giganten", "store", "741852789", 55, 3000000));
        Controller.addCompany(new Company("fona", "store", "9875462", 69, 2000000));
        Controller.addCompany(new Company("data world", "store", "7965428", 10, 1000000));
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
    public void adds() {
        
        
        
    }
//    @Test
//    public void addPhone() {
//        
//        //add phone to person
//        //existing person new phone
//        Person p1 = Controller.getPerson(1);
//        p1.addPhonies(new Phone(452587598,"add phone test"));
//        Controller.editPerson(p1);
//
//        
//    }
    @Test
    public void hobbify(){
        Controller.hobbifyPerson(1, "Running");
        Controller.hobbifyPerson(2, "Biking");
        Controller.hobbifyPerson(3, "Gaming");
        Controller.hobbifyPerson(3, "Masturbating");
        Controller.hobbifyPerson(2, "Running");
        Controller.hobbifyPerson(2, "Gaming");
        
        Person p1 = Controller.getPerson(1);
        Person p2 = Controller.getPerson(2);
        Person p3 = Controller.getPerson(3);
        Controller.addPhone(p1,new Phone(452587598,"add phone test"));
        Controller.addPhone(p2,new Phone(452587598,"add phone test"));
        Controller.addPhone(p3,new Phone(452587598,"add phone test"));
        
        
        
    }
    @Test
    public void addAddress(){
        
        //existing person new address
        Person p1 = Controller.getPerson(1);
        p1.setHood(new Address("hovedgaden", "22 2th", new CityInfo(2800, "lyngby")));
        Controller.editPerson(p1);
        
        //existing person and address
        Person p2 = Controller.getPerson(2);
        Address hood2 = Controller.addAddress(new Address("gade 2","2",new CityInfo(1337,"somewhere")));
        p2.setHood(hood2);
        Controller.editPerson(p2);
        
        
    }
//    @Test
//    public void delete(){
//        Controller.deletePerson(3);
//        Controller.deleteCompany(3);
//        Controller.deletePhone(525875984);
//    
//    }
    
}
