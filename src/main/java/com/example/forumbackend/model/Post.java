package com.example.forumbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "HIDDEN")
    private Boolean hidden;

    @Column(name = "POST_TEXT", nullable = false)
    private String postText;

    @Column(name = "POST_DATE_TIME")
    private Timestamp postDate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "USER_ID")
    private User user;
}
