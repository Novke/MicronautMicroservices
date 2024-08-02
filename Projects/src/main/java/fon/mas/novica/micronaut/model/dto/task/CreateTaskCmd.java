package fon.mas.novica.micronaut.model.dto.task;

import fon.mas.novica.micronaut.model.entity.TaskEntity;

import java.time.LocalDate;

public record CreateTaskCmd(
        String title,
        String description,
        TaskEntity.Priority priority,
        LocalDate dueDate,
        LocalDate endDate,
        Long supervisorId,
        Long assigneeId
) {
}
