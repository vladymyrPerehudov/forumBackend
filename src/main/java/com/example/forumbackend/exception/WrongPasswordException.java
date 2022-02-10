package com.example.forumbackend.exception;

public class WrongPasswordException extends Exception{

    public WrongPasswordException(){
        super("Password is wrong");
    }
}
