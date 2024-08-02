package fon.mas.novica.micronaut.model.dto.project;

import fon.mas.novica.micronaut.model.dto.task.TaskInfo;
import fon.mas.novica.micronaut.model.enums.Status;

import java.time.LocalDate;
import java.util.List;

public class ProjectDetails {

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate endDate;
    private LocalDate createdDate;
    private Status status;
    private String supervisorName;
    private List<TaskInfo> tasks;

    public ProjectDetails(String name, String description, LocalDate startDate, LocalDate dueDate, LocalDate endDate, LocalDate createdDate, Status status, String supervisorName, List<TaskInfo> tasks) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.status = status;
        this.supervisorName = supervisorName;
        this.tasks = tasks;
    }
}
