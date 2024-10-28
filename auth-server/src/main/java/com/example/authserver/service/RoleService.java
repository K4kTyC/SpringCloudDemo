package com.example.authserver.service;

import com.example.authserver.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role getDefaultRoleReference();

    List<Role> getAll();

    Optional<Role> findByName(String roleName);

    Optional<Role> findById(Long roleId);

    Role save(Role role);

    boolean existsByName(String newRoleName);
}
