package com.example.authserver.mapper;

import com.example.authserver.dto.NewUserDto;
import com.example.authserver.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(NewUserDto newUserDto);

}
