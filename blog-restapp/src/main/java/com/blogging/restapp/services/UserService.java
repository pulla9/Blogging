package com.blogging.restapp.services;

import com.blogging.auth.models.Credentials;
import com.blogging.entities.User;
import com.blogging.restapp.exceptions.UserAlreadyExistsException;

import java.util.List;

/**
 * Created by pulla6 on 20/12/16.
 */
public interface UserService {
    public boolean register(User user) throws UserAlreadyExistsException;

    public User getUserDetails(int userId);

    public List<User> getAllUsers();
}
