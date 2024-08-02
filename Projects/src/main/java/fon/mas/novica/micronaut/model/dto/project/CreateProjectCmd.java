package fon.mas.novica.micronaut.model.dto.project;

import java.time.LocalDate;

public record CreateProjectCmd(
        String name,
        String description,
        LocalDate startDate,
        LocalDate dueDate,
        Long supervisorId
) {
}
