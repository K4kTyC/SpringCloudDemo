package com.example.authserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    private Long id;

    private String username;

    private String role;

}
