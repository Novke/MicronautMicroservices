package fon.mas.novica.micronaut.model.dto.task;

import fon.mas.novica.micronaut.model.entity.TaskEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Serdeable.Deserializable
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
