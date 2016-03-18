/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import entity.Address;
import entity.CityInfo;
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
import javax.ws.rs.DELETE;
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
     *
     * @return an instance of java.lang.String work
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
    public String getContactInfo() {
        return JSONConverter.getJSON(JSONConverter.getJSONfromContact(Controller.getPersonlist()));
    }

    @GET
    @Path("/contactinfo/{id}")
    @Produces("application/json")
    public String getPersonContactInfo(@PathParam("id") int id) {
        return JSONConverter.getJSON(JSONConverter.getJSONfromContact(Controller.getPerson(id)));
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createPerson(String person) {
        Person p = JSONConverter.createPersonfromJSON(person);
        Person newp = new Person(p.getFirstName(), p.getLastName());
        newp.setEmail(p.getEmail());
        newp = Controller.addPerson(newp);
        for (Hobby h : p.getHobbies()) {
            Controller.addHobby(new Hobby(h.getName(), h.getDisc()));
            Controller.hobbifyPerson(newp.getId(), h.getName());
        }
        Address a = Controller.addAddress(new Address(p.getHood().getStreet(), p.getHood().getAdditonalInfo()));
        CityInfo ci = Controller.addCityInfo(new CityInfo(p.getHood().getShityInfo().getZipCode(), p.getHood().getShityInfo().getCity()));
        Controller.addressify(newp, a);
        Controller.addressCityInfo(a  , ci);
        for (Phone ph : p.getPhonies()) {
            Controller.addPhone(new Phone(ph.getNumber(), ph.getDisc()));
            Controller.phonyfy(newp, ph);
        }
        
        return getPerson(newp.getId());
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public String deletePerson(@PathParam("id") int id) {
        Person p = Controller.deletePerson(id);
        return JSONConverter.getJSON(JSONConverter.getJSONFromPerson(p));
    }

    /**
     * PUT method for updating or creating an instance of PersonEndpoint
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
