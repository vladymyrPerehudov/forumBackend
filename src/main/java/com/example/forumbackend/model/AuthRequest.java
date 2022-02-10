package com.example.forumbackend.model;

import lombok.Data;

@Data
public class AuthRequest {

    private String loginOrEmail;
    private String password;
}