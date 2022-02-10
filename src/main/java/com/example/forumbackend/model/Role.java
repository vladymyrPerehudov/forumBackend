package com.example.forumbackend.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Entity(name = "role")
public class Role implements GrantedAuthority {

    private final static Role ADMIN = new Role(1, "ADMIN");
    private final static Role USER = new Role(2, "USER");

    public static Role getAdminRole() {
        return ADMIN;
    }

    public static Role getDefaultRole() {
        return USER;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ROLE_NAME", unique = true, nullable = false)
    private String roleName;

    private Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    protected Role() {
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + getRoleName().toUpperCase();
    }
}