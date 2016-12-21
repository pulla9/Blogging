package com.blogging.restapp;

import com.blogging.restapp.resources.ArticleResource;
import com.blogging.restapp.resources.UserResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pulla6 on 20/12/16.
 */
public class WebApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public WebApplication() {
        singletons.add(new UserResource());
        singletons.add(new ArticleResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
