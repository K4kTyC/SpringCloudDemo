package com.example.authserver.facade;

import com.example.authserver.dto.NewUserDto;
import com.example.authserver.dto.UserDto;
import com.example.authserver.entity.Role;
import com.example.authserver.entity.User;
import com.example.authserver.exception.ActionForbiddenException;
import com.example.authserver.exception.EntityNotFoundException;
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
        user.setRole(roleService.getDefaultRoleReference());

        userService.createUser(user);
    }

    public List<UserDto> getAll() {
        List<User> users = userService.getAll();
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    public String updateUserRole(Long userId, String newRoleName) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No user with id: " + userId));
        if (user.getRole().getName().equals("ROLE_ADMIN")) {
            throw new ActionForbiddenException("Modifying Role of Admin users is forbidden");
        }
        if (user.getRole().getName().equals(newRoleName)) {
            return newRoleName;
        }

        Role role = roleService.findByName(newRoleName)
                .orElseGet(() -> roleService.save(new Role(newRoleName)));

        user.setRole(role);
        userService.save(user);

        return role.getName();
    }
}
