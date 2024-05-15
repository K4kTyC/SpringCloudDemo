package com.example.authserver.service.impl;

import com.example.authserver.entity.Role;
import com.example.authserver.repository.RoleRepository;
import com.example.authserver.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    public Role getDefaultRole() {
        return roleRepository.findByName("ROLE_USER").orElseThrow();
    }
}
