package com.example.forumbackend.controller;

import com.example.forumbackend.exception.UserAlreadyExistsException;
import com.example.forumbackend.exception.UserDoesNotExistsException;
import com.example.forumbackend.exception.WrongPasswordException;
import com.example.forumbackend.model.AuthRequest;
import com.example.forumbackend.model.AuthResponse;
import com.example.forumbackend.model.Role;
import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.UserRepository;
import com.example.forumbackend.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        user.setRole(Role.getDefaultRole());
        if (user.equals(userRepository.findByLogin(user.getLogin())) ||
                user.equals(userRepository.findByEmail(user.getLogin()))) {
            throw new UserAlreadyExistsException(user.getLogin());
        }
        userRepository.save(user);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) throws UserDoesNotExistsException, WrongPasswordException {
        User user = userRepository.findByLogin(request.getLoginOrEmail());
        if (user == null) {
            user = userRepository.findByEmail(request.getLoginOrEmail());
            if (user == null) {
                throw new UserDoesNotExistsException(request.getLoginOrEmail());
            }
        }
        if (user.getPassword() == null || !request.getPassword().equals(user.getPassword())) {
            throw new WrongPasswordException();
        }
        return new AuthResponse(jwtProvider.generateToken(user.getLogin()));
    }
}
