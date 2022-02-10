package com.example.forumbackend.controller;

import com.example.forumbackend.exception.UserDoesNotExistsException;
import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Integer id) throws UserDoesNotExistsException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserDoesNotExistsException("User with id: " + id));
    }

    @PostMapping(value = "/create")
    public void createUser(@RequestBody User newUser) {
        userRepository.save(newUser);
    }

    @PatchMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public void updateUser(@RequestBody User updatedUser, @RequestParam Integer id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDoesNotExistsException("User with id: " + id));
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setBanDate(updatedUser.getBanDate());
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteUser(@RequestBody User user) {
        userRepository.delete(user);
    }
}
