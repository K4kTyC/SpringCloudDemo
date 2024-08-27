package com.example.authserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class RoleDto {

    private Long id;

    private String name;

    private List<PrivilegeDto> privileges;
}
