package fon.mas.novica.micronaut.model.dto.notification;

import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Serdeable
public record TaskCompletedNotif(
        String firstName,
        String lastName,
        Long taskId,
        String taskTitle,
        String taskPriority,
        String email,
        String assigneeName,
        LocalDate dueDate
) {
    @Override
    public String toString() {
        return "TaskCompletedNotif{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskPriority='" + taskPriority + '\'' +
                ", email='" + email + '\'' +
                ", assigneeName='" + assigneeName + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }

    public String basicInfo(){
        return String.format("Supervisor %s %s for the task id: %d", firstName, lastName, taskId);
    }
}
