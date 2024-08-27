package com.example.authserver.mapper;

import com.example.authserver.dto.PrivilegeDto;
import com.example.authserver.entity.Privilege;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {

    PrivilegeDto toDto(Privilege privilege);
}
