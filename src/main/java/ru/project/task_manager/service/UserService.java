package ru.project.task_manager.service;

import org.springframework.stereotype.Service;
import ru.project.task_manager.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> updateById(Long id, User user);

    void deleteById(Long id);
}
