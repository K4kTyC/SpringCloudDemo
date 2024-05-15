package com.example.authserver.facade;

import com.example.authserver.dto.NewUserDto;
import com.example.authserver.entity.User;
import com.example.authserver.mapper.UserMapper;
import com.example.authserver.service.RoleService;
import com.example.authserver.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final RoleService roleService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;


    public void createUser(NewUserDto userDto) {
        User user = userMapper.toEntity(userDto);
        if (userService.userExists(user)) {
            throw new RuntimeException("User with such username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(roleService.getDefaultRole()));

        userService.createUser(user);
    }
}
