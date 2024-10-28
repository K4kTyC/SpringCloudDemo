package com.example.authserver.facade;

import com.example.authserver.dto.RoleDto;
import com.example.authserver.dto.UpdateRolePrivilegeDto;
import com.example.authserver.dto.UpdateUserRoleDto;
import com.example.authserver.entity.Privilege;
import com.example.authserver.entity.Role;
import com.example.authserver.exception.ActionForbiddenException;
import com.example.authserver.exception.DuplicateEntityException;
import com.example.authserver.exception.EntityNotFoundException;
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

    public UpdateUserRoleDto updateRoleName(Long roleId, UpdateUserRoleDto userRoleDto) {
        String newRoleName = userRoleDto.getRoleName();

        Role role = roleService.findById(roleId).orElseThrow(() -> new RuntimeException("No role with id: " + roleId));
        if (role.getName().equals("ROLE_ADMIN")) {
            throw new ActionForbiddenException("Modifying Admin Role is forbidden");
        }
        if (role.getName().equals(newRoleName)) {
            return new UpdateUserRoleDto(role.getId(), role.getName());
        }
        if (roleService.existsByName(newRoleName)) {
            throw new DuplicateEntityException("Role with such name already exists");
        }

        role.setName(newRoleName);
        Role updatedRole = roleService.save(role);

        return new UpdateUserRoleDto(updatedRole.getId(), updatedRole.getName());
    }

    public UpdateRolePrivilegeDto addPrivilege(Long roleId, UpdateRolePrivilegeDto rolePrivilegeDto) {
        String privilegeName = rolePrivilegeDto.getPrivilegeName().toLowerCase();

        Role role = roleService.findById(roleId).orElseThrow(() -> new EntityNotFoundException("No role with id: " + roleId));
        Collection<Privilege> privileges = role.getPrivileges();
        Privilege privilege;

        Optional<Privilege> existingPrivilege = privilegeService.findByName(privilegeName);
        if (existingPrivilege.isPresent()) {
            boolean isPrivilegeAssigned = privileges.stream()
                    .anyMatch(p -> p.getName().equals(privilegeName));
            if (isPrivilegeAssigned) {
                throw new DuplicateEntityException("Such privilege is already assigned to the role");
            }
            privilege = existingPrivilege.get();
        } else {
            privilege = privilegeService.save(new Privilege(privilegeName));
        }

        privileges.add(privilege);
        role.setPrivileges(privileges);
        roleService.save(role);

        return new UpdateRolePrivilegeDto(privilege.getId(), privilege.getName());
    }

    public void deletePrivilege(Long roleId, Long privilegeId) {
        Role role = roleService.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("No role with id: " + roleId));

        if (role.getName().equals("ROLE_ADMIN")) {
            throw new ActionForbiddenException("Modifying Admin Role is forbidden");
        }

        Collection<Privilege> privileges = role.getPrivileges();
        Privilege privilegeToDelete = privileges.stream()
                .filter(privilege -> privilege.getId().equals(privilegeId))
                .findAny()
                .orElseThrow(() -> new EntityNotFoundException("Privilege not found"));

        if (privilegeToDelete.getRoles().size() == 1) {
            privilegeService.delete(privilegeToDelete.getId());
        }

        privileges.remove(privilegeToDelete);
        role.setPrivileges(privileges);
        roleService.save(role);
    }
}
