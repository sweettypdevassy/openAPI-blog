package com.blog.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class Endpoints {
    @GET // HTTP GET request
    @Produces(MediaType.TEXT_PLAIN) // Produces plain text response
    public String sayHello() {
        return "Hello, Open Liberty with MicroProfile!";
    }

    @POST // HTTP POST request
    @Consumes(MediaType.APPLICATION_JSON) // Consumes JSON payload
    @Produces(MediaType.TEXT_PLAIN) // Produces plain text response
    public String postMessage(String message) {
        System.out.println(message);
        return "Successfully recieved message";
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateResource(@PathParam("id") String id, String message) {
        System.out.println(id +" "+ message);
        return "Updated the message";
    }
}
