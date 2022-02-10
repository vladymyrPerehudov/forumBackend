package com.example.forumbackend.controller;

import com.example.forumbackend.model.Post;
import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    @PostMapping(value = "/create")
    public void createPost(@RequestBody User user, String postText) {
        if (postText != null) {
            Post post = new Post();
            post.setPostText(postText);
            post.setUser(user);
            postRepository.save(post);
        }
    }

    @PatchMapping(value = "/{id}")
    public void hidePost(@RequestParam Post post) {
        post.setHidden(true);
    }
}
