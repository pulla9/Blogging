package com.blogging.restapp.exceptions;

/**
 * Created by pulla6 on 20/12/16.
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String s) {
        super(s);
    }
}
