package com.example.forumbackend.exception;

public class UserDoesNotExistsException extends Exception {

    public UserDoesNotExistsException(String user) {
        super(user + " does not exists");
    }
}
