package ru.project.task_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.task_manager.dto.UserDto;
import ru.project.task_manager.entity.User;
import ru.project.task_manager.repository.UserRepository;
import ru.project.task_manager.service.UserMapper;
import ru.project.task_manager.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> dtoList = userMapper.toDtoList(allUsers);
        if(dtoList.isEmpty()) {
            throw new IllegalArgumentException("Users not found!");
        } else return dtoList;
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isEmpty()) {
            throw new IllegalArgumentException("User with that id is not found");
        } else return Optional.of(userMapper.toDtoEntity(foundUser.get()));
    }

    @Override
    public UserDto save(UserDto userDto) {
        User userEntity = userMapper.toUserEntity(userDto);
        User savedUser = userRepository.save(userEntity);
        return userMapper.toDtoEntity(savedUser);
    }

    @Override
    public Optional<UserDto> updateById(Long id, UserDto userDto) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()) {
            User newUser = userMapper.toUserEntity(userDto);
            User updatedUser = foundUser.get();
            updatedUser.setUsername(newUser.getUsername());
            updatedUser.setPassword(newUser.getPassword());
            updatedUser.setRole(newUser.getRole());
            updatedUser.setTasks(newUser.getTasks());
            updatedUser.setEmail(newUser.getEmail());
            User savedUser = userRepository.save(updatedUser);
            return Optional.of(userMapper.toDtoEntity(savedUser));
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
