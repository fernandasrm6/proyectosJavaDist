/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyPath;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author fernandasramirezm
 */
@Path("MyPath/{username}")
public class MyPathResource {

@GET @Produces("text/plain")
public String getText(@PathParam("username")String username){ return "UserName:"+username;
} 
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MyPathResource
     */
    public MyPathResource() {
    }

    /**
     * Retrieves representation of an instance of MyPath.MyPathResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml(@QueryParam("name")String name, @QueryParam("age")String age) {
        //TODO return proper representation object
        return "Hi "+name+"age "+age;
    }

    /**
     * PUT method for updating or creating an instance of MyPathResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
        System.out.println(content);
    }
    @POST
@Produces("text/html")
public String postHtml( @FormParam("name")String name, @FormParam("age")String age) {
return "Hi "+ name+ " age: "+age; }
}


