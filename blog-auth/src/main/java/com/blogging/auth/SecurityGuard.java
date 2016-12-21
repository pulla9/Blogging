package com.blogging.auth;

import com.blogging.auth.exceptions.CredentialsMismatchException;
import com.blogging.auth.models.Credentials;

/**
 * Created by pulla6 on 20/12/16.
 */
public interface SecurityGuard {

    public boolean verify(Credentials credentials) throws CredentialsMismatchException;
}
