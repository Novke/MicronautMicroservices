package fon.mas.novica.micronaut.io;

import fon.mas.novica.micronaut.model.dto.notification.NewAssignmentNotif;
import fon.mas.novica.micronaut.model.dto.notification.TaskCompletedNotif;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Client(id = "notifications-ms")
@ExecuteOn(TaskExecutors.BLOCKING)
public interface NotificationsServiceClient {
    @Post("/notify/task-completed")
    void notifyTaskCompleted(@Body TaskCompletedNotif notification);
    @Post("/notify/new-assignment")
    void notifyAssignee(@Body NewAssignmentNotif notification);
}
