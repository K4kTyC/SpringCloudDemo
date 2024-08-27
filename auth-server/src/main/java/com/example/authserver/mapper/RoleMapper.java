package com.example.authserver.mapper;

import com.example.authserver.dto.RoleDto;
import com.example.authserver.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PrivilegeMapper.class)
public interface RoleMapper {

    RoleDto toDto(Role role);
}
