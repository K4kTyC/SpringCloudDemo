package com.example.authserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewUserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
