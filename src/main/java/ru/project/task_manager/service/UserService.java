package ru.project.task_manager.service;

import ru.project.task_manager.dto.UserDto;
import ru.project.task_manager.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(UserDto userDto);

    Optional<UserDto> updateById(Long id, UserDto userDto);

    void deleteById(Long id);
}
