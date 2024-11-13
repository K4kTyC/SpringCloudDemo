package com.example.authserver.service.impl;

import com.example.authserver.entity.Role;
import com.example.authserver.entity.User;
import com.example.authserver.repository.UserRepository;
import com.example.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username " + username + " not found"));

        Collection<GrantedAuthority> authorities = getAuthorities(user.getRole());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    private Collection<GrantedAuthority> getAuthorities(Role role) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        authorities.addAll(role.getPrivileges()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .toList());

        return authorities;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public boolean userExists(User user) {
        return userRepository.existsByUsername(user.getUsername());
    }

    public List<User> getAll() {
         return userRepository.findAll();
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Slice<User> findByRoleId(Long roleId) {
        Pageable firstFiveUsers = PageRequest.ofSize(5);
        return userRepository.findByRoleId(roleId, firstFiveUsers);
    }

    public long countByRoleId(Long roleId) {
        return userRepository.countByRoleId(roleId);
    }
}
