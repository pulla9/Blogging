package com.blogging.auth.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.blogging.auth.SecurityGuard;
import com.blogging.auth.exceptions.CredentialsMismatchException;
import com.blogging.auth.models.Credentials;

/**
 * Created by pulla6 on 20/12/16.
 */
public class TokenSecurityGuard implements SecurityGuard {

    public boolean verify(Credentials credentials) throws CredentialsMismatchException {
        try {
            JWT jwt = JWT.decode(credentials.getToken());
        } catch (JWTDecodeException exception) {
            //Invalid token
        }
        return false;
    }
}