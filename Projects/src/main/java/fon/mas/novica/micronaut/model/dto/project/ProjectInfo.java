package fon.mas.novica.micronaut.model.dto.project;


import fon.mas.novica.micronaut.model.enums.Status;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Serdeable.Serializable
public class ProjectInfo {

    private String name;
    private String description;
    private Status status;
    private LocalDate createdDate;
    private LocalDate startDate;
    private LocalDate dueDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
