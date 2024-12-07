package com.blog.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class Endpoints {
    @GET // HTTP GET request
    @Produces(MediaType.TEXT_PLAIN) // Produces plain text response
    public String sayHello() {
        return "Hello, Open Liberty with MicroProfile!";
    }
}
