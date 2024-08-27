package com.example.authserver.service;

import com.example.authserver.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void createUser(User user);

    boolean userExists(User user);

    List<User> getAll();

    Optional<User> findById(Long userId);

    void save(User user);
}
