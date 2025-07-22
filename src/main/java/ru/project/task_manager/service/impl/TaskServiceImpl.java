package ru.project.task_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.task_manager.dto.TaskDto;
import ru.project.task_manager.entity.Task;
import ru.project.task_manager.repository.TaskRepository;
import ru.project.task_manager.service.TaskMapper;
import ru.project.task_manager.service.TaskService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;


    @Override
    public List<TaskDto> findAll() {
        List<Task> allTasks = taskRepository.findAll();
        if(allTasks.isEmpty()) {
            throw new IllegalArgumentException("Tasks not found!");
        } else return mapper.toDtoList(allTasks);
    }

    @Override
    public Optional<TaskDto> findById(Long id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if(foundTask.isEmpty()) {
            throw new IllegalArgumentException("Task with that id is not found");
        } else return Optional.of(mapper.toDtoEntity(foundTask.get()));
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        Task savedTask = mapper.toTaskEntity(taskDto);
        Task task = taskRepository.save(savedTask);
        return mapper.toDtoEntity(task);
    }

    @Override
    public Optional<TaskDto> updateById(Long id, TaskDto taskDto) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if(foundTask.isPresent()) {
            Task newTask = mapper.toTaskEntity(taskDto);
            Task updatedTask = foundTask.get();
            updatedTask.setTitle(newTask.getTitle());
            updatedTask.setDescription(newTask.getDescription());
            updatedTask.setStatus(newTask.getStatus());
            updatedTask.setUser(newTask.getUser());
            Task savedTask = taskRepository.save(updatedTask);
            return Optional.of(mapper.toDtoEntity(savedTask));
        } else throw new IllegalArgumentException("Task not found");
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
