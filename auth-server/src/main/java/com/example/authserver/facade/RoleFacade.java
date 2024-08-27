package com.example.authserver.facade;

import com.example.authserver.dto.RoleDto;
import com.example.authserver.entity.Privilege;
import com.example.authserver.entity.Role;
import com.example.authserver.mapper.RoleMapper;
import com.example.authserver.service.PrivilegeService;
import com.example.authserver.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleService roleService;
    private final PrivilegeService privilegeService;

    private final RoleMapper roleMapper;


    public List<RoleDto> getAll() {
        List<Role> roles = roleService.getAll();
        return roles.stream()
                .map(roleMapper::toDto)
                .toList();
    }

    public void addPrivilege(Long roleId, String privilegeName) {
        Role role = roleService.findById(roleId).orElseThrow(() -> new RuntimeException("No role with id: " + roleId));
        Collection<Privilege> privileges = role.getPrivileges();
        Privilege privilege;

        Optional<Privilege> existingPrivilege = privilegeService.findByName(privilegeName);
        if (existingPrivilege.isPresent()) {
            boolean isPrivilegeAssigned = privileges.stream()
                    .anyMatch(p -> p.getName().equals(privilegeName));
            if (isPrivilegeAssigned) {
                throw new RuntimeException("Such privilege is already assigned to the role");
            }
            privilege = existingPrivilege.get();
        } else {
            privilege = privilegeService.save(new Privilege(privilegeName));
        }

        privileges.add(privilege);
        role.setPrivileges(privileges);
        roleService.save(role);
    }

    public void deletePrivilege(Long roleId, Long privilegeId) {
        Role role = roleService.findById(roleId)
                .orElseThrow(() -> new RuntimeException("No role with id: " + roleId));

        Collection<Privilege> privileges = role.getPrivileges();
        Privilege privilegeToDelete = privileges.stream()
                .filter(privilege -> privilege.getId().equals(privilegeId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Privilege not found"));

        if (privilegeToDelete.getRoles().size() == 1) {
            privilegeService.delete(privilegeToDelete.getId());
        }

        privileges.remove(privilegeToDelete);
        role.setPrivileges(privileges);
        roleService.save(role);
    }
}
