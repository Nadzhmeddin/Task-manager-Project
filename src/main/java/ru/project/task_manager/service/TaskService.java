package ru.project.task_manager.service;

import ru.project.task_manager.dto.TaskDto;
import ru.project.task_manager.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskDto> findAll();

    Optional<TaskDto> findById(Long id);

    TaskDto save(TaskDto taskDto);

    Optional<TaskDto> updateById(Long id, TaskDto taskDto);

    void deleteById(Long id);
}
