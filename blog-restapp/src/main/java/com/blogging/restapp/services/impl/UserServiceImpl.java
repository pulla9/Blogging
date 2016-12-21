package com.blogging.restapp.services.impl;

import com.blogging.auth.models.Credentials;
import com.blogging.entities.User;
import com.blogging.entities.dao.UserDAO;
import com.blogging.entities.exceptions.UserNotFoundException;
import com.blogging.restapp.exceptions.UserAlreadyExistsException;
import com.blogging.restapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pulla6 on 20/12/16.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public boolean register(User user) throws UserAlreadyExistsException {
        User actualUser = userDAO.getUserByEmail(user.getEmail());
        if (actualUser == null) {
            userDAO.save(user);
            return true;
        } else
            throw new UserAlreadyExistsException();
    }

    public User getUserDetails(int userId) {
        User user = userDAO.getUser(userId);
        user.setPassword(null);
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        for (User user : users)
            user.setPassword(null);
        return users;
    }
}