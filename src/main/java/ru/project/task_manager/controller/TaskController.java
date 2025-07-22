package ru.project.task_manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.task_manager.entity.Task;
import ru.project.task_manager.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Task>> updateById(@PathVariable Long id, @RequestBody Task task) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.updateById(id, task));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
