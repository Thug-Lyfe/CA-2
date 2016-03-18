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
import com.google.gson.JsonParser;
import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Alex
 */
public class JSONConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static JsonObject getJSONFromPerson(Person p) {
        JsonObject person = new JsonObject();
        JsonObject contactInfo = new JsonObject();
        JsonObject name = new JsonObject();
        JsonObject home = new JsonObject();
        JsonArray hobbies = new JsonArray();
        JsonArray phonies = new JsonArray();
        person.addProperty("id", p.getId());
        if (p.getFirstName() != null) {
            name.addProperty("firstname", p.getFirstName());
        } else {
            name.addProperty("firstname", "");
        }
        if (p.getLastName() != null) {
            name.addProperty("lastname", p.getLastName());
        } else {
            name.addProperty("lastname", "");
        }
        if (p.getEmail() != null) {
            contactInfo.addProperty("email", p.getEmail());
        } else {
            contactInfo.addProperty("email", "");
        }
        if (p.getHood().getStreet() != null) {
            home.addProperty("street", p.getHood().getStreet());
        } else {
            home.addProperty("street", "");
        }
        if (p.getHood().getAdditonalInfo() != null) {
            home.addProperty("addinfo", p.getHood().getAdditonalInfo());
        } else {
            home.addProperty("addinfo", "");
        }
        if (p.getPhonies() != null || !p.getPhonies().isEmpty()) {
            for (int i = 0; i < p.getPhonies().size(); i++) {
                JsonObject phone = new JsonObject();
                if (Integer.valueOf(p.getPhonies().get(i).getNumber()) != null) {
                    phone.addProperty("phonenumber", p.getPhonies().get(i).getNumber());
                } else {
                    phone.addProperty("phonenumber", "");
                }
                if (p.getPhonies().get(i).getDisc() != null) {
                    phone.addProperty("phonedescription", p.getPhonies().get(i).getDisc());
                } else {
                    phone.addProperty("phonedescription", "");
                }
                phonies.add(phone);
            }

        } else {
            phonies.add(new JsonObject());
        }
        if (Integer.valueOf(p.getHood().getShityInfo().getZipCode()) != null) {
            home.addProperty("zipcode", p.getHood().getShityInfo().getZipCode());
        } else {
            home.addProperty("zipcode", "");
        }
        if (p.getHood().getShityInfo().getCity() != null) {
            home.addProperty("city", p.getHood().getShityInfo().getCity());
        } else {
            home.addProperty("city", "");
        }
        if (p.getHobbies() != null || !p.getHobbies().isEmpty()) {
            
            for (int i = 0; i < p.getHobbies().size(); i++) {
                JsonObject hobby = new JsonObject();
                if (p.getHobbies().get(i).getName() != null) {
                    hobby.addProperty("hobbyname", p.getHobbies().get(i).getName());
                } else {
                    hobby.addProperty("hobbyname", "");
                }
                if (p.getHobbies().get(i).getDisc() != null) {
                    hobby.addProperty("hobbydescription", p.getHobbies().get(i).getDisc());
                } else {
                    hobby.addProperty("hobbydescription", "");
                }
                hobbies.add(hobby);
                
            }
        } else {
            hobbies.add(new JsonObject());
        }
//        
//        person.addProperty("firstname", p.getFirstName());
//        person.addProperty("lastname", p.getLastName());
//        //person.addProperty("email", p.getEmail());
//        //person.addProperty("street", p.getHood().getStreet());
//        person.add("contact", contactInfo);
//        person.addProperty("addinfo", p.getHood().getAdditonalInfo());
//        person.addProperty("zipcode", p.getHood().getShityInfo().getZipCode());
//        person.addProperty("city", p.getHood().getShityInfo().getCity());
//        //person.addProperty("hobbies", hobbies.getAsString());
//        
//        
//        
        contactInfo.add("Phone", phonies);
        person.add("Name", name);
        person.add("Address", home);
        person.add("ContactInfo", contactInfo);
        person.add("Hobbies", hobbies);
        return person;
    }

    public static JsonArray getJSONFromPerson(List<Person> list) {
        JsonArray people = new JsonArray();
        for (Person p : list) {
            people.add(getJSONFromPerson(p));
        }
        return people;
    }

    public static String getJSON(JsonElement voorhees) {

        return gson.toJson(voorhees);
    }

    public static JsonArray getJSONfromContact(List<Person> list) {
        JsonArray peeps = getJSONFromPerson(list);
        JsonArray contacts = new JsonArray();

        for (int i = 0; i < peeps.size(); i++) {
            JsonObject check = peeps.get(i).getAsJsonObject();
            if (check.has("ContactInfo")) {
                if (check.has("Hobbies")) {
                    check.remove("Hobbies");
                }
                if(check.has("Address")){
                    check.remove("Address");
                }
                contacts.add(check);
            }
        }
        return contacts;
    }

    public static JsonObject getJSONfromContact(Person p) {
        JsonObject temp = getJSONFromPerson(p);

        if (temp.has("ContactInfo")) {
            if (temp.has("Hobbies")) {
                temp.remove("Hobbies");
            }
            if(temp.has("Address")){
                temp.remove("Address");
            }
        }

        return temp;
    }
    
    public static Person createPersonfromJSON(String voorhees){
        //Jsonparser stuff
        JsonObject something = new JsonParser().parse(voorhees).getAsJsonObject();
        
        //parsing address
        JsonObject address = something.getAsJsonObject("Address");
        
        //adding address
        Address a = new Address(address.get("street").getAsString(), address.get("addinfo").getAsString());
        CityInfo ci = new CityInfo(address.get("zipcode").getAsInt(), address.get("city").getAsString());
        a.setShityInfo(ci);
        ci.addHoods(a);
        
        //adding name
        JsonObject name = something.getAsJsonObject("Name");
        Person p = new Person(name.get("firstname").getAsString(), name.get("lastname").getAsString());
        p.setHood(a);
        
        //adding email
        JsonObject contact = something.getAsJsonObject("ContactInfo");
        p.setEmail(contact.get("email").getAsString());
        
        //adding phones
        JsonArray phonies = contact.getAsJsonArray("Phone");
        Iterator<JsonElement> ponies = phonies.iterator();
        while(ponies.hasNext()){
            JsonObject temp = ponies.next().getAsJsonObject();
            Phone phone = new Phone(temp.get("phonenumber").getAsInt(), temp.get("phonedescription").getAsString());
            p.addPhonies(phone);
            phone.setHoe(p);
        }
        
        //adding hobbies to people and people to hobbies
        JsonArray hobbies = something.getAsJsonArray("Hobbies");
        Iterator<JsonElement> homies = hobbies.iterator();
        while(homies.hasNext()){
            JsonObject temp = homies.next().getAsJsonObject();
            Hobby hobby = new Hobby(temp.get("hobbyname").getAsString(), temp.get("hobbydescription").getAsString());
            p.addHobby(hobby);
            hobby.addPersons(p);
        }
        
        return p;
    } 
}
