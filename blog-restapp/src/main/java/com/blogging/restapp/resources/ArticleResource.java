package com.blogging.restapp.resources;

import com.blogging.auth.SecurityGuard;
import com.blogging.auth.exceptions.CredentialsMismatchException;
import com.blogging.auth.models.Credentials;
import com.blogging.entities.Article;
import com.blogging.restapp.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by pulla6 on 20/12/16.
 */
@Component
@Path("/article")
public class ArticleResource {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SecurityGuard tokenSecurityGuard;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArticles(@QueryParam("category") Integer category,
                                   @QueryParam("user_id") Integer userId,
                                   @HeaderParam("auth-token") String token) {
        try {
            verifyCred(token);
            List<Article> articleList;
            if (userId == null && category == null)
                return Response.status(Response.Status.BAD_REQUEST).build();
            if (category == null && userId != null)
                articleList = articleService.getAllArticles(userId);
            else
                articleList = articleService.getAllArticlesByCategory(category);
            return Response.ok(articleList).build();
        } catch (CredentialsMismatchException cme) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createArticle(Article article, @HeaderParam("auth-token") String token) {
        try {
            verifyCred(token);
            articleService.createService(article);
        } catch (CredentialsMismatchException cme) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id") int id, @HeaderParam("auth-token") String token) {
        try {
            verifyCred(token);
            articleService.deleteArticle(id);
            return Response.ok().build();
        } catch (CredentialsMismatchException cme) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
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
