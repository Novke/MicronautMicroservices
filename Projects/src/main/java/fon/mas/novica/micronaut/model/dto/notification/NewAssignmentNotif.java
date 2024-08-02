package fon.mas.novica.micronaut.model.dto.notification;

import java.time.LocalDate;

public record NewAssignmentNotif(
        String firstName,
        String lastName,
        String supervisor,
        String email,
        LocalDate dueDate,
        String priority,
        Long taskId
) {

    @Override
    public String toString() {
        return "NewAssignmentNotif{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", email='" + email + '\'' +
                ", dueDate=" + dueDate +
                ", priority='" + priority + '\'' +
                ", taskId=" + taskId +
                '}';
    }

    public String basicInfo(){
        return String.format("Assignee %s %s for the task id %d", firstName, lastName, taskId);
    }
}
