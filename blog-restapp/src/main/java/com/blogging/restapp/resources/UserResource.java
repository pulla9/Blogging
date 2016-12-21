package com.blogging.restapp.resources;

import com.blogging.auth.SecurityGuard;
import com.blogging.auth.exceptions.CredentialsMismatchException;
import com.blogging.auth.models.Credentials;
import com.blogging.auth.token.TokenGenerator;
import com.blogging.entities.Article;
import com.blogging.entities.User;
import com.blogging.restapp.exceptions.UserAlreadyExistsException;
import com.blogging.restapp.services.ArticleService;
import com.blogging.restapp.services.UserService;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.specimpl.BuiltResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by pulla6 on 20/12/16.
 */
@Component
@Path("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityGuard upSecurityGuard;

    @Autowired
    private SecurityGuard tokenSecurityGuard;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@HeaderParam("auth-token") String token) {
        try {
            boolean res = verifyCred(token);
            List<User> users = userService.getAllUsers();
            return Response.ok(users).build();
        } catch (CredentialsMismatchException cme) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id, @HeaderParam("auth-token") String token) {
        try {
            boolean res = verifyCred(token);
            User user = userService.getUserDetails(id);
            return Response.ok(user).build();
        } catch (CredentialsMismatchException cme) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        try {
            userService.register(user);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            return Response.status(Response.Status.CONFLICT).entity("Email Already Exists").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(Credentials credentials) {
        String token = null;
        try {
            upSecurityGuard.verify(credentials);
            token = TokenGenerator.generate(credentials.getEmail());
        } catch (CredentialsMismatchException cme) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().header("auth-token", token).build();
    }

    private boolean verifyCred(String token) throws CredentialsMismatchException {
        if (token == null)
            throw new CredentialsMismatchException();
        else {
            Credentials credentials = new Credentials();
            credentials.setToken(token);
            tokenSecurityGuard.verify(credentials);
        }
        return false;
    }
}