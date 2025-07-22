package ru.project.task_manager.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {

    TO_DO("to do"),
    IN_PROGRESS("in progress"),
    DONE("done"),
    CANCELLED("cancelled");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }
}
