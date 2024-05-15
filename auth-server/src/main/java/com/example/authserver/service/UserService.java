package com.example.authserver.service;

import com.example.authserver.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(User user);

    boolean userExists(User user);
}
