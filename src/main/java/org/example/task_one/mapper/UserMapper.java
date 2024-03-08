package org.example.task_one.mapper;

import org.example.task_one.dto.CreateUserDto;
import org.example.task_one.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUserEntity(CreateUserDto userDto);
}
