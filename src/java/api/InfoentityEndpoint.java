/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author Alex
 */
@Path("infoentity")
public class InfoentityEndpoint {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InfoentityEndpoint
     */
    public InfoentityEndpoint() {
    }

    /**
     * Retrieves representation of an instance of api.InfoentityEndpoint
     * @return an instance of java.lang.String
     */


    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public String deleteId(@PathParam("id") int id){
        return null;
    }
    
    /**
     * PUT method for updating or creating an instance of InfoentityEndpoint
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    
}
