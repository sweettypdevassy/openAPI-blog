package com.blog.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("/api")
@OpenAPIDefinition(
    info = @Info(
        title = "Blog API",
        version = "1.0",
        description = "This is an example API using MicroProfile OpenAPI",
        contact = @Contact(name = "Sweetty", email = "sweetty@gmail.com"),
        license = @License(name = "Apache 2.0", url = "https://apache.org/licenses/LICENSE-2.0.html")
    ),
    servers = @Server(url = "http://localhost:9081/openapi-demo", description = "Localhost Server"),
    tags = {
        @Tag(name = "Blog", description = "Operations related to blog management")
    },
    security = @SecurityRequirement(name = "jwtAuth")
)
@SecuritySchemes({
    @SecurityScheme(
        securitySchemeName = "jwtAuth",
        description = "JWT Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
    )
})

@Path("/hello")
public class Endpoints extends Application {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Get a greeting message", description = "Returns a simple greeting from Open Liberty")
    @APIResponse(responseCode = "200", description = "Successful response", content = @Content(
        mediaType = MediaType.TEXT_PLAIN,
        schema = @Schema(implementation = String.class, example = "Hello, Open Liberty with MicroProfile!")
    ))
    public String sayHello() {
        return "Hello, Open Liberty with MicroProfile!!";
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get User by ID", description = "Returns the user details for a given ID")
    @APIResponse(responseCode = "200", description = "Successful Response", content = @Content(
        mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = User.class)
    ))
    @APIResponse(responseCode = "404", description = "User not found")
    public User getUser(
        @Parameter(description = "User ID", required = true) 
        @PathParam("id") String id) {
        
        return new User(id, "John Doe");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Post a message", description = "Accepts a message and returns confirmation")
    @APIResponse(responseCode = "200", description = "Message received successfully")
    @APIResponse(responseCode = "400", description = "Invalid request format")
    @SecurityRequirement(name = "jwtAuth")
    public String postMessage(String message) {
        System.out.println(message);
        return "Successfully received message";
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Update a resource", description = "Updates a message for a given ID")
    @APIResponse(responseCode = "200", description = "Message updated successfully")
    @APIResponse(responseCode = "404", description = "Resource not found")
    @SecurityRequirement(name = "jwtAuth")
    public String updateResource(@PathParam("id") String id, String message) {
        System.out.println(id + " " + message);
        return "Updated the message";
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Delete a resource", description = "Deletes a message with the given ID")
    @APIResponse(responseCode = "200", description = "Message deleted successfully")
    @APIResponse(responseCode = "404", description = "Resource not found")
    @SecurityRequirement(name = "jwtAuth")
    public String deleteResource(@PathParam("id") String id) {
        System.out.println("Deleting resource with ID: " + id);
        return "Resource with ID " + id + " deleted successfully";
    }
}

// ✅ User model moved outside as a standalone class
@Schema(description = "User model")
class User {
    @Schema(description = "Unique user ID", example = "123")
    public String id;

    @Schema(description = "User name", example = "John Doe")
    public String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
