package com.example.authserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.example.authserver.dto.validations.ValidationConstants.REGEXP_ROLE_SYNTAX;

@Getter @Setter
@NoArgsConstructor
public class RoleDto {

    private Long id;

    @NotBlank(message = "Should not be empty")
    @Pattern(regexp = REGEXP_ROLE_SYNTAX, message = "Invalid Role syntax")
    private String name;

    private List<PrivilegeDto> privileges;
}
