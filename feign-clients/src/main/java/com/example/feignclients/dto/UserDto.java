package com.example.feignclients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter @Setter
@AllArgsConstructor
public class UserDto {
    String username;
    String password;
    Collection<String> authorities;
}
