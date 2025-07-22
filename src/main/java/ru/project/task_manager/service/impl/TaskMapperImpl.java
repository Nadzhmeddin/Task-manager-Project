package ru.project.task_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.task_manager.dto.TaskDto;
import ru.project.task_manager.entity.Task;
import ru.project.task_manager.entity.User;
import ru.project.task_manager.enums.TaskStatus;
import ru.project.task_manager.repository.UserRepository;
import ru.project.task_manager.service.TaskMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskMapperImpl implements TaskMapper {

    private final UserRepository userRepository;

    @Override
    public Task toTaskEntity(TaskDto taskDto) {
        Task task = new Task();
        String status = taskDto.getStatus().toLowerCase();
        if(status.equals(TaskStatus.CANCELLED.getStatus())) {
            task.setStatus(TaskStatus.CANCELLED);
        } else if(status.equals(TaskStatus.DONE.getStatus())) {
            task.setStatus(TaskStatus.DONE);
        } else if(status.equals(TaskStatus.TO_DO.getStatus())) {
            task.setStatus(TaskStatus.TO_DO);
        }
        Optional<User> foundUser = userRepository.findById(taskDto.getUser_id());
        if(foundUser.isPresent()) {
            User user = foundUser.get();
            task.setUser(user);
            task.setDescription(taskDto.getDescription());
            task.setTitle(taskDto.getTitle());
            return task;
        } else throw new IllegalArgumentException("User with that id not found");
    }

    @Override
    public TaskDto toDtoEntity(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setUser_id(task.getUser().getId());
        taskDto.setStatus(task.getStatus().getStatus());
        return taskDto;
    }

    @Override
    public List<TaskDto> toDtoList(List<Task> taskList) {
        List<TaskDto> dtoList = new ArrayList<>(taskList.size());
        for (Task task : taskList) {
            dtoList.add(toDtoEntity(task));
        }
        return dtoList;
    }
}
