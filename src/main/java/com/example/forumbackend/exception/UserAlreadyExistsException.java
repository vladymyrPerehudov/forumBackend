package com.example.forumbackend.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String user){
        super(user + " already exists");
    }
}
