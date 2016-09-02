package com.mushan;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by mazhibin on 16/8/22
 */
@Path("/")
public class Test {
    @GET
    @Path("/")
    public String index() {
        return "index";
    }


    @POST
    @Path("/test")
    public String test() {
        return "test";
    }

    @POST
    @Path("/testJson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Man testJson(Man man){
        man.age = 100;
        return man;
    }
}
