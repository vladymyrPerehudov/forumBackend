package com.example.forumbackend.repository;

import com.example.forumbackend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByLogin(String login);

    User findByEmail(String email);
}