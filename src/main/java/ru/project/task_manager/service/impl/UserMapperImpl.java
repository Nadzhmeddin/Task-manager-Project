package ru.project.task_manager.service.impl;

import org.springframework.stereotype.Service;
import ru.project.task_manager.dto.UserDto;
import ru.project.task_manager.entity.User;
import ru.project.task_manager.enums.UserRole;
import ru.project.task_manager.service.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUserEntity(UserDto userDto) {
        User user = new User();
        String role = userDto.getRole().toLowerCase();
        if(role.equals(UserRole.USER.getRole())) {
            user.setRole(UserRole.USER);
        } else if(role.equals(UserRole.ADMIN.getRole())) {
            user.setRole(UserRole.ADMIN);
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        return user;
    }

    @Override
    public UserDto toDtoEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole().getRole());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<User> usersEntity) {
        List<UserDto> dtoList = new ArrayList<>(usersEntity.size());
        for (User user : usersEntity) {
            dtoList.add(toDtoEntity(user));
        }
        return dtoList;
    }


}
