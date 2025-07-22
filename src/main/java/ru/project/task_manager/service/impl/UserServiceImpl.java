package ru.project.task_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.task_manager.entity.User;
import ru.project.task_manager.repository.UserRepository;
import ru.project.task_manager.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> allUsers = userRepository.findAll();
        if(allUsers.isEmpty()) {
            throw new IllegalArgumentException("Users not found!");
        } else return allUsers;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isEmpty()) {
            throw new IllegalArgumentException("User with that id is not found");
        } else return foundUser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateById(Long id, User user) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()) {
            User updatedUser = foundUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRole(user.getRole());
            updatedUser.setTasks(user.getTasks());
            updatedUser.setEmail(user.getEmail());
            User savedUser = userRepository.save(updatedUser);
            return Optional.of(savedUser);
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
