package fon.mas.novica.micronaut.model;

import fon.mas.novica.micronaut.service.MyEmail;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable.Deserializable
public class TaskCompletedNotif implements MyEmail {

    private String firstName;
    private String lastName;
    private Long taskId;
    private String taskTitle;
    private String taskPriority;
    private String email;
    private String assigneeName;
    private String dueDate;

    public TaskCompletedNotif(String firstName, String lastName, Long taskId, String taskTitle, String taskPriority, String email, String assigneeName, String dueDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskPriority = taskPriority;
        this.email = email;
        this.assigneeName = assigneeName;
        this.dueDate = dueDate;
    }

    @Override
    public String getRecipient() {
        return email;
    }

    @Override
    public List<String> getCc() {
        return null;
    }

    @Override
    public List<String> getBcc() {
        return null;
    }

    @Override
    public String getSubject() {
        return "Task completed!";
    }

    @Override
    public String getHtmlBody() {
        return null;
    }

    @Override
    public String getTextBody() {
        return String.format("""
                    Hello %s %s,
                    
                    The task "%s" with ID %d, issued for assignee %s has been marked as completed. As a task supervisor it is in your
                    responsibility to assure the task has been completed properly.
                    
                    PRIORITY: %s
                    Due Date: %s
                    
                    Thank you for completing the task on time!
                    
                    ====================================================================================================================
                    THIS EMAIL AND ANY ATTACHMENTS IS INTENDED SOLELY FOR DESIGNATED RECIPIENTS AND MAY CONTAIN CONFIDENTAL INFORMATION.
                    IF YOU ARE NOT THE INTENDED RECIPIENT (%s) NOTIFY THE ORGANIZATION IMMEDIATELY AND DELETE ALL COPIES.
                    ANY UNAUTHORIZED REVIEW, USE OR DISCLOSURE IS PROHIBITED.
                    ====================================================================================================================
                    """,
                firstName, lastName, taskTitle, taskId, assigneeName, taskPriority, dueDate, email);
        }

    @Override
    public String getReplyTo() {
        return null;
    }
}

