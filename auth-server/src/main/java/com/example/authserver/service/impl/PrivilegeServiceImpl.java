package com.example.authserver.service.impl;

import com.example.authserver.entity.Privilege;
import com.example.authserver.repository.PrivilegeRepository;
import com.example.authserver.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;


    public Privilege save(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    public void delete(Long id) {
        privilegeRepository.deleteById(id);
    }

    public Optional<Privilege> findByName(String privilegeName) {
        return privilegeRepository.findByName(privilegeName);
    }
}
