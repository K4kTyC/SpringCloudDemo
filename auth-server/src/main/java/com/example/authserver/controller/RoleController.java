package com.example.authserver.controller;

import com.example.authserver.dto.RoleDto;
import com.example.authserver.facade.RoleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/{roleId}/privileges")
    @PreAuthorize("hasAuthority('roles.write')")
    public ResponseEntity<Void> addPrivilege(@PathVariable(name = "roleId") Long roleId,
                                             @RequestBody String privilegeName) {
        roleFacade.addPrivilege(roleId, privilegeName.toLowerCase());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{roleId}/privileges/{privilegeId}")
    @PreAuthorize("hasAuthority('roles.write')")
    public ResponseEntity<Void> deletePrivilege(@PathVariable(name = "roleId") Long roleId,
                                          @PathVariable(name = "privilegeId") Long privilegeId) {
        roleFacade.deletePrivilege(roleId, privilegeId);
        return ResponseEntity.ok().build();
    }
}
