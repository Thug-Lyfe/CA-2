/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.Person;
import java.util.List;

/**
 *
 * @author Alex
 */
public class JSONConverter {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static JsonObject getJSONFromPerson(Person p){
        JsonObject person = new JsonObject();
        person.addProperty("id", p.getId());
        person.addProperty("firstname", p.getFirstName());
        person.addProperty("lastname", p.getLastName());
        person.addProperty("email", p.getEmail());
        person.addProperty("street", p.getHood().getStreet());
        person.addProperty("addinfo", p.getHood().getAdditonalInfo());
        for (int i = 0; i < p.getPhonies().size(); i++) {
            person.addProperty("phonenumber " + i+1, p.getPhonies().get(i).getNumber());
            person.addProperty("phonedescription" + i+1, p.getPhonies().get(i).getDisc());
        }
        person.addProperty("zipcode", p.getHood().getShityInfo().getZipCode());
        person.addProperty("city", p.getHood().getShityInfo().getCity());
        for (int i = 0; i < p.getHobbies().size(); i++) {
            person.addProperty("hobby " +i+1, p.getHobbies().get(i).getName());
            person.addProperty("hobbydescription " +i+1, p.getHobbies().get(i).getDisc());
        }
        return person;
    } 
    public static JsonArray getJSONFromPerson(List<Person> list){
        JsonArray people = new JsonArray();
        for (Person p : list) {
            people.add(getJSONFromPerson(p));
        }
        return people;
    }
    
    public static String getJSON(JsonElement voorhees){
        
        return gson.toJson(voorhees);
    }
}