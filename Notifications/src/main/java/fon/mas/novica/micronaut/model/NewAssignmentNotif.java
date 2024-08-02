package fon.mas.novica.micronaut.model;

public record NewAssignmentNotif (

     String firstName,
     String lastName,
     String supervisor,
     String email,
     String dueDate,
     String priority,
     Long taskId
)
{
}
