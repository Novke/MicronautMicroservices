package fon.mas.novica.micronaut.io;

import fon.mas.novica.micronaut.model.dto.notification.NewAssignmentNotif;
import fon.mas.novica.micronaut.model.dto.notification.TaskCompletedNotif;
import io.micronaut.retry.annotation.Fallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Fallback
public class NotificationsServiceFallback implements NotificationsServiceClient{
    @Override
    public void notifyTaskCompleted(TaskCompletedNotif newAssignmentNotif) {
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.error("NOTIFICATION SENDING FAILED FOR ASSIGNEE {} FOR THE TASK ID: {}",
                newAssignmentNotif.firstName() + " " + newAssignmentNotif.lastName(),
                newAssignmentNotif.taskId());
    }

    @Override
    public void notifyAssignee(NewAssignmentNotif notification) {
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.error("NOTIFICATION SENDING FAILED FOR SUPERVISOR {} FOR THE TASK ID: {}",
                notification.firstName() + " " + notification.lastName(),
                notification.taskId());
    }
}
