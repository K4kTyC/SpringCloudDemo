package com.example.authserver.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRoleDto {

    @Pattern(regexp = "^ROLE_[A-Z0-9]{1,15}$", message = "Invalid Role syntax")
    private String role;
}
