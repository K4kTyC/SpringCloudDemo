package com.example.authserver.controller;

import com.example.authserver.dto.UpdateUserRoleDto;
import com.example.authserver.dto.UserDto;
import com.example.authserver.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;


    @GetMapping()
    @PreAuthorize("hasAuthority('roles.read')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userFacade.getAll());
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasAuthority('roles.write')")
    public ResponseEntity<UpdateUserRoleDto> updateUserRole(@PathVariable(name = "id") Long userId,
                                                            @RequestBody @Validated UpdateUserRoleDto userRoleDto) {
        String newRoleName = userFacade.updateUserRole(userId, userRoleDto.getRole());
        return ResponseEntity.ok(new UpdateUserRoleDto(newRoleName));
    }
}
