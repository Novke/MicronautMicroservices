package fon.mas.novica.micronaut.model.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import fon.mas.novica.micronaut.model.entity.TaskEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Serdeable.Deserializable
public record CreateTaskCmd(
        String title,
        String description,
        TaskEntity.Priority priority,
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate dueDate,
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate endDate,
        Long supervisorId,
        Long assigneeId
) {
}
