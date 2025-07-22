package ru.project.task_manager.service.impl;

import org.springframework.stereotype.Service;
import ru.project.task_manager.dto.TaskDto;
import ru.project.task_manager.entity.Task;
import ru.project.task_manager.service.TaskMapper;

@Service
public class TaskMapperImpl implements TaskMapper {


    @Override
    public Task toTaskEntity(TaskDto taskDto) {
        return null;
    }

    @Override
    public TaskDto toDtoEntity(Task task) {
        return null;
    }
}
