package com.mushan;

import javax.ws.rs.*;

/**
 * Created by mazhibin on 16/8/22
 */
@Path("/library")
public class Library {

    @GET
    @Path("/books")
    public String getBooks() {
        return "book";
    }

    @GET
    @Path("/book/{isbn}")
    public String getBook(@PathParam("isbn") String id) {
       return id;
    }

    @PUT
    @Path("/book/{isbn}")
    public void addBook(@PathParam("isbn") String id, @QueryParam("name") String name) {

    }

    @DELETE
    @Path("/book/{id}")
    public void removeBook(@PathParam("id") String id) {

    }

}