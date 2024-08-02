package fon.mas.novica.micronaut.model.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Serdeable.Deserializable
public record CreateProjectCmd(
        String name,
        String description,
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate startDate,
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate dueDate,
        Long supervisorId
) {
}
