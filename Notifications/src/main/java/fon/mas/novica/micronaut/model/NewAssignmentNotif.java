package fon.mas.novica.micronaut.model;

import fon.mas.novica.micronaut.service.MyEmail;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable.Deserializable
public class NewAssignmentNotif implements MyEmail {

    private final String firstName;
    private final String lastName;
    private final String supervisor;
    private final String email;
    private final String dueDate;
    private final String priority;
    private final Long taskId;

    public NewAssignmentNotif(String firstName, String lastName, String supervisor, String email, String dueDate, String priority, Long taskId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.supervisor = supervisor;
        this.email = email;
        this.dueDate = dueDate;
        this.priority = priority;
        this.taskId = taskId;
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
        return "New assignment!";
    }

    @Override
    public String getHtmlBody() {
        return null;
    }

    @Override
    public String getTextBody() {
        return String.format("""
                        New task has been assigned to %s %s by the supervisor %s
                        You are expected to finish the task no later than %s
                        
                        PRIORITY: %s
                        Check the details for the task using id = %s
                        
                        ====================================================================================================================
                        THIS EMAIL AND ANY ATTACHMENTS IS INTENDED SOLELY FOR DESIGNATED RECIPIENTS AND MAY CONTAIN CONFIDENTAL INFORMATION.
                        IF YOU ARE NOT THE INTENDED RECIPIENT (%s) NOTIFY THE ORGANIZATION IMMEDIATELY AND DELETE ALL COPIES.
                        ANY UNAUTHORIZED REVIEW, USE OR DISCLOSURE IS PROHIBITED.
                        ====================================================================================================================
                        """,
                firstName,
                lastName,
                supervisor,
                dueDate,
                priority.toUpperCase(),
                taskId,
                email);
    }

    @Override
    public String getReplyTo() {
        return null;
    }

    @Override
    public String toString() {
        return "NewAssignmentNotif{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", email='" + email + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority='" + priority + '\'' +
                ", taskId=" + taskId +
                '}';
    }
}