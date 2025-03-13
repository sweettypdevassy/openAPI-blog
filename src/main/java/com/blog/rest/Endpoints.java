package com.blog.rest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
@ApplicationPath("/api")
@OpenAPIDefinition(
    info = @Info(
        title = "Blog API",
        version = "1.0",
        description = "This is an example API using MicroProfile OpenAPI",
        contact = @Contact(name = "Sweetty", email = "sweettypdevassy@gmail.com")
    ),
    tags = {
        @Tag(name = "Blog", description = "Operations related to blog management")
    }
)
@Path("/hello")
public class Endpoints extends Application {
    @GET // HTTP GET request
    @Produces(MediaType.TEXT_PLAIN) // Produces plain text response
    @Operation(summary = "Get a greeting message", description = "Returns a simple greeting from Open Liberty")
    @APIResponse(responseCode = "200", description = "Successful response",content = @Content(
            mediaType = MediaType.TEXT_PLAIN,
            schema = @Schema(implementation = String.class, example = "Hello, Open Liberty with MicroProfile!")
        ))
    public String sayHello() {
        return "Hello, Open Liberty with MicroProfile!!";
    }
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.TEXT_PLAIN)  // Explicitly set response type
    @Operation(summary = "Get User by ID", description = "Returns the user ID as a string")
    @APIResponse(
        responseCode = "200",
        description = "Successful Response",
        content = @Content(mediaType = MediaType.TEXT_PLAIN, 
                           schema = @Schema(implementation = String.class))
    )
    public String getUser(
        @Parameter(description = "User ID", required = true) 
        @PathParam("id") String id) {
        
        return "User ID: " + id;
    }
    @Schema(description = "User model")
    public class User {
        @Schema(description = "Unique user ID", example = "123")
        public String id;

        @Schema(description = "User name", example = "John Doe")
        public String name;
    }
    @POST // HTTP POST request
    @Consumes(MediaType.APPLICATION_JSON) // Consumes JSON payload
    @Produces(MediaType.TEXT_PLAIN) // Produces plain text response
    @Operation(summary = "Post a message", description = "Accepts a message and returns confirmation")
    @APIResponse(responseCode = "200", description = "Message received successfully")
    public String postMessage(String message) {
        System.out.println(message);
        return "Successfully recieved message";
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Update a resource", description = "Updates a message for a given ID")
    @APIResponse(responseCode = "200", description = "Message updated successfully")
    public String updateResource(@PathParam("id") String id, String message) {
        System.out.println(id +" "+ message);
        return "Updated the message";
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Delete a resource", description = "Deletes a message with the given ID")
    @APIResponse(responseCode = "200", description = "Message deleted successfully")
    public String deleteResource(@PathParam("id") String id) {
        System.out.println("Deleting resource with ID: " + id);
        return "Resource with ID " + id + " deleted successfully";
    }


}



