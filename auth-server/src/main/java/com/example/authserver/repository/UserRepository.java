package com.example.authserver.repository;

import com.example.authserver.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Slice<User> findByRoleId(Long roleId, Pageable pageable);

    long countByRoleId(Long roleId);
}
