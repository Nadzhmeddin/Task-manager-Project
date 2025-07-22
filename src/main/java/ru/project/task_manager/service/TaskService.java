package ru.project.task_manager.service;

import ru.project.task_manager.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task save(Task task);

    Optional<Task> updateById(Long id, Task task);

    void deleteById(Long id);
}
