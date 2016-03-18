/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.Controller;
import facade.JSONConverter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Alex
 */
@Path("person")
public class PersonEndpoint {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonEndpoint
     */
    public PersonEndpoint() {
    }

    /**
     * Retrieves representation of an instance of api.PersonEndpoint
     * @return an instance of java.lang.String
     * work
     */
    @GET
    @Path("/complete")
    @Produces("application/json")
    public String getJson() {
        return JSONConverter.getJSON(JSONConverter.getJSONFromPerson(Controller.getPersonlist()));
    }
    
    @GET
    @Path("/complete/{id}")
    @Produces("application/json")
    public String getPerson(@PathParam("id") int id) {
        return JSONConverter.getJSON(JSONConverter.getJSONFromPerson(Controller.getPerson(id)));
    }
    
    @GET
    @Path("/contactinfo")
    @Produces("application/json")
    public String getContactInfo(){
        return JSONConverter.getJSON(JSONConverter.getJSONfromContact(Controller.getPersonlist()));
    }
    
    @GET
    @Path("/contactinfo/{id}")
    @Produces("application/json")
    public String getPersonContactInfo(@PathParam("id") int id){
        return JSONConverter.getJSON(JSONConverter.getJSONfromContact(Controller.getPerson(id)));
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createPerson(String person){
        Person p = JSONConverter.createPersonfromJSON(person);
        Controller.addPerson(p);
        for (Hobby h : p.getHobbies()) {
            Controller.addHobby(h);
//            Controller.hobbifyPerson(p.getId(), h.getName());
        }
        Controller.addAddress(p.getHood());
        Controller.addCityInfo(p.getHood().getShityInfo());
//        Controller.addressify(p, p.getHood());
//        Controller.addressCityInfo(p.getHood()  , p.getHood().getShityInfo());
        for (Phone ph : p.getPhonies()) {
            Controller.addPhone(p, ph);
        }
        return person;
    }
    
    /**
     * PUT method for updating or creating an instance of PersonEndpoint
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
