package com.blogging.entities.exceptions;

/**
 * Created by pulla6 on 20/12/16.
 */
public class UserNotFoundException extends Exception {

    private String message;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
