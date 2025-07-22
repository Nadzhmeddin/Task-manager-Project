package ru.project.task_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.task_manager.entity.Task;
import ru.project.task_manager.repository.TaskRepository;
import ru.project.task_manager.service.TaskService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        List<Task> allTasks = taskRepository.findAll();
        if(allTasks.isEmpty()) {
            throw new IllegalArgumentException("Tasks not found!");
        } else return allTasks;
    }

    @Override
    public Optional<Task> findById(Long id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if(foundTask.isEmpty()) {
            throw new IllegalArgumentException("Task with that id is not found");
        } else return foundTask;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> updateById(Long id, Task task) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if(foundTask.isPresent()) {
            Task updatedTask = foundTask.get();
            updatedTask.setTitle(task.getTitle());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setStatus(task.getStatus());
            updatedTask.setUser(task.getUser());
            Task savedTask = taskRepository.save(updatedTask);
            return Optional.of(savedTask);
        } else throw new IllegalArgumentException("Task not found");
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
