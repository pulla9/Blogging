package com.blogging.auth.exceptions;

/**
 * Created by pulla6 on 20/12/16.
 */
public class CredentialsMismatchException extends Exception{
    public CredentialsMismatchException() {
        super();
    }

    public CredentialsMismatchException(String s) {
        super(s);
    }
}
