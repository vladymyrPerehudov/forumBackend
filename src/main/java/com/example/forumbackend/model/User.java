package com.example.forumbackend.model;

import com.example.forumbackend.model.converter.PasswordConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "LOGIN", unique = true, nullable = false, length = 30)
    private String login;

    @Convert(converter = PasswordConverter.class)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 30)
    private String lastName;

    @Column(name = "BAN_DATE")
    private Date banDate;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;
}
