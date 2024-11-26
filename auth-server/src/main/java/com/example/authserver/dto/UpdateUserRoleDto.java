package com.example.authserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.authserver.dto.validations.ValidationConstants.REGEXP_ROLE_SYNTAX;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRoleDto {

    private Long roleId;

    @NotBlank(message = "Should not be empty")
    @Pattern(regexp = REGEXP_ROLE_SYNTAX, message = "Invalid Role syntax")
    private String roleName;
}
