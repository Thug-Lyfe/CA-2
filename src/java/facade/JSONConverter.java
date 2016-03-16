/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import java.util.List;

/**
 *
 * @author Alex
 */
public class JSONConverter {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static String getJSONFromPerson(Person p){
        return gson.toJson(p);
    } 
    public static String getJSONFromPerson(List<Person> list){
        return gson.toJson(list);
    }
    
    
}
