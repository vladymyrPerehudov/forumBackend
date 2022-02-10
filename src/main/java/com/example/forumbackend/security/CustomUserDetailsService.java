package com.example.forumbackend.security;

import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String loginOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(loginOrEmail);
        if (user == null) {
            user = userRepository.findByEmail(loginOrEmail);
        }
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User: %s, not found", loginOrEmail));
        }
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
