package com.example.authserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class UsersOfRoleDto {

    private Long roleId;

    private boolean hasMore;

    private List<String> usernames;
}
