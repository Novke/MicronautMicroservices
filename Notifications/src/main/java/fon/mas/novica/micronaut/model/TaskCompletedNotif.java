package fon.mas.novica.micronaut.model;

public record TaskCompletedNotif (

     String firstName,
     String lastName,
     Long taskId,
     String taskTitle,
     String taskPriority,
     String email,
     String assigneeName,
     String dueDate
){
}
