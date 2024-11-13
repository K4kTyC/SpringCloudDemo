package com.example.authserver.controller;

import com.example.authserver.dto.RoleDto;
import com.example.authserver.dto.UpdateRolePrivilegeDto;
import com.example.authserver.dto.UpdateUserRoleDto;
import com.example.authserver.dto.UsersOfRoleDto;
import com.example.authserver.facade.RoleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleFacade roleFacade;


    @GetMapping()
    @PreAuthorize("hasAuthority('roles.read')")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(roleFacade.getAll());
    }

    @PutMapping("/{roleId}")
    @PreAuthorize("hasAuthority('roles.write')")
    public ResponseEntity<UpdateUserRoleDto> updateRoleName(@PathVariable(name = "roleId") Long roleId,
                                               @RequestBody @Validated UpdateUserRoleDto userRoleDto) {
        return ResponseEntity.ok(roleFacade.updateRoleName(roleId, userRoleDto));
    }

    @PostMapping("/{roleId}/privileges")
    @PreAuthorize("hasAuthority('roles.write')")
    public ResponseEntity<UpdateRolePrivilegeDto> addPrivilege(@PathVariable(name = "roleId") Long roleId,
                                             @RequestBody UpdateRolePrivilegeDto rolePrivilegeDto) {
        return ResponseEntity.ok(roleFacade.addPrivilege(roleId, rolePrivilegeDto));
    }

    @DeleteMapping("/{roleId}/privileges/{privilegeId}")
    @PreAuthorize("hasAuthority('roles.write')")
    public ResponseEntity<Void> deletePrivilege(@PathVariable(name = "roleId") Long roleId,
                                          @PathVariable(name = "privilegeId") Long privilegeId) {
        roleFacade.deletePrivilege(roleId, privilegeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('roles.write')")
    public ResponseEntity<List<UsersOfRoleDto>> findUsersOfEachRole() {
        return ResponseEntity.ok(roleFacade.findUsersOfEachRole());
    }

    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('roles.write')")
    public ResponseEntity<Void> deleteRole(@PathVariable(name = "roleId") Long roleId) {
        roleFacade.deleteRole(roleId);
        return ResponseEntity.ok().build();
    }
}
