package com.example.authserver.service;

import com.example.authserver.entity.Privilege;

import java.util.Optional;

public interface PrivilegeService {

    Privilege save(Privilege privilege);

    void delete(Long id);

    Optional<Privilege> findByName(String privilegeName);
}
