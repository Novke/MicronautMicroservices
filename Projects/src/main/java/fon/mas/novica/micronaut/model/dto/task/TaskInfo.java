package fon.mas.novica.micronaut.model.dto.task;


import fon.mas.novica.micronaut.model.entity.TaskEntity;
import fon.mas.novica.micronaut.model.enums.Status;

import java.time.LocalDate;

public class TaskInfo {
    private Long id;
    private String title;
    private Status status;
    private TaskEntity.Priority priority;
    private LocalDate dueDate;
    private String assigneeName;
    private String supervisorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TaskEntity.Priority getPriority() {
        return priority;
    }

    public void setPriority(TaskEntity.Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }
}
