package com.example.authserver.service.impl;

import com.example.authserver.entity.Role;
import com.example.authserver.repository.RoleRepository;
import com.example.authserver.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    public Role getDefaultRoleReference() {
        return roleRepository.getReferenceById(1L);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    public Optional<Role> findById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
