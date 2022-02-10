package com.example.forumbackend.security;

import com.example.forumbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomUserDetails implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    private static PasswordEncoder passwordEncoder;

    public CustomUserDetails(PasswordEncoder passwordEncoder) {
        CustomUserDetails.passwordEncoder = passwordEncoder;
    }

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User user) {
        CustomUserDetails custom = new CustomUserDetails(passwordEncoder);
        custom.login = user.getLogin();
        custom.password = passwordEncoder.encode(user.getPassword());
        custom.grantedAuthorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return custom;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
