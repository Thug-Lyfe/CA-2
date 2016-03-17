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

    public static JsonObject getJSONFromPerson(Person p) {
        JsonObject person = new JsonObject();
        JsonObject contactInfo = new JsonObject();
        JsonObject name = new JsonObject();
        JsonArray hobbies = new JsonArray();
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
            contactInfo.addProperty("street", p.getHood().getStreet());
        } else {
            contactInfo.addProperty("street", "");
        }
        if (p.getHood().getAdditonalInfo() != null) {
            contactInfo.addProperty("addinfo", p.getHood().getAdditonalInfo());
        } else {
            contactInfo.addProperty("addinfo", "");
        }
        if (p.getPhonies() != null) {
            for (int i = 0; i < p.getPhonies().size(); i++) {
                if (Integer.valueOf(p.getPhonies().get(i).getNumber()) != null) {
                    contactInfo.addProperty("phonenumber " + (i + 1), p.getPhonies().get(i).getNumber());
                } else {
                    contactInfo.addProperty("phonenumber", "");
                }
                if (p.getPhonies().get(i).getDisc() != null) {
                    contactInfo.addProperty("phonedescription" + (i + 1), p.getPhonies().get(i).getDisc());
                } else {
                    contactInfo.addProperty("phonedescription", "");
                }
            }

        } else {
            contactInfo.addProperty("phonenumber", "");
            contactInfo.addProperty("phonedescription", "");
        }
        if (Integer.valueOf(p.getHood().getShityInfo().getZipCode()) != null) {
            contactInfo.addProperty("zipcode", p.getHood().getShityInfo().getZipCode());
        } else {
            contactInfo.addProperty("zipcode", "");
        }
        if (p.getHood().getShityInfo().getCity() != null) {
            contactInfo.addProperty("city", p.getHood().getShityInfo().getCity());
        } else {
            contactInfo.addProperty("city", "");
        }
        if (p.getHobbies() != null) {
            
            for (int i = 0; i < p.getHobbies().size(); i++) {
                JsonObject hobby = new JsonObject();
                if (p.getHobbies().get(i).getName() != null) {
                    hobby.addProperty("hobbyname", p.getHobbies().get(i).getName());
                } else {
                    hobby.addProperty("hobbyname", "");
                }
                if (p.getHobbies().get(i).getDisc() != null) {
                    hobby.addProperty("hobbydescription " + (i + 1), p.getHobbies().get(i).getDisc());
                } else {
                    hobby.addProperty("hobbydescription", "");
                }
                hobbies.add(hobby);
                
            }
        } else {
            hobbies.add(new JsonObject());
        }
        person.addProperty("Name", name.getAsString());
        person.addProperty("ContactInfo", contactInfo.getAsString());
        person.addProperty("Hobbies", hobbies.getAsString());
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
                if (check.has("Name")) {
                    check.remove("Name");
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
            if (temp.has("Name")) {
                temp.remove("Name");
            }
        }

        return temp;
    }
}
