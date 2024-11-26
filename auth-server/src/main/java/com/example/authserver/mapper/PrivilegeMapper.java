package com.example.authserver.mapper;

import com.example.authserver.dto.PrivilegeDto;
import com.example.authserver.entity.Privilege;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {

    @Mapping(target = "roles", ignore = true)
    Privilege toEntity(PrivilegeDto privilegeDto);

    PrivilegeDto toDto(Privilege privilege);
}
