package ru.project.task_manager.service;

import ru.project.task_manager.dto.TaskDto;
import ru.project.task_manager.entity.Task;

import java.util.List;

public interface TaskMapper {

    Task toTaskEntity(TaskDto taskDto);

    TaskDto toDtoEntity(Task task);

    public List<TaskDto> toDtoList(List<Task> taskList);
}
