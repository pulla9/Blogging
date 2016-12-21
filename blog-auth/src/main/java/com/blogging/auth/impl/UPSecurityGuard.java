package com.blogging.auth.impl;

import com.blogging.auth.SecurityGuard;
import com.blogging.auth.exceptions.CredentialsMismatchException;
import com.blogging.auth.models.Credentials;
import com.blogging.entities.User;
import com.blogging.entities.dao.UserDAO;
import com.blogging.entities.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pulla6 on 20/12/16.
 */
public class UPSecurityGuard implements SecurityGuard {

    @Autowired
    private UserDAO userDAO;

    public boolean verify(Credentials credentials) throws CredentialsMismatchException {
        User user = userDAO.getUserByEmail(credentials.getEmail());
        if (user == null) {
            throw new CredentialsMismatchException();
        }
        if (user.getPassword().equals(credentials.getPassword()))
            return true;
        else
            throw new CredentialsMismatchException();
    }
}