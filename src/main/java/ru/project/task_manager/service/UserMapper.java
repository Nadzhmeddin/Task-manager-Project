package ru.project.task_manager.service;

import ru.project.task_manager.dto.UserDto;
import ru.project.task_manager.entity.User;

import java.util.List;

public interface UserMapper {

    User toUserEntity(UserDto userDto);

    UserDto toDtoEntity(User user);

    List<UserDto> toDtoList(List<User> usersEntity);
}
