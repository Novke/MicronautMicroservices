package fon.mas.novica.micronaut.rest;

import fon.mas.novica.micronaut.model.NewAssignmentNotif;
import fon.mas.novica.micronaut.model.TaskCompletedNotif;
import fon.mas.novica.micronaut.service.EmailService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import jakarta.ws.rs.core.Response;

@Controller("/notify")
public class NotificationsController {

    @Inject
    private EmailService emailService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Post("/new-assignment")
    public Response notifyAssignee(@Body NewAssignmentNotif notification){
        log.info("NEW ASSIGNMENT RECIEVED! {}", notification);
        emailService.send(notification);
        return Response.ok("Uspesno").build();
    }
    @Post("/task-completed")
    public Response notifySupervisorTaskCompleted(@Body TaskCompletedNotif notification){
        log.info("TASK COMPLETED! {}", notification);
        return null;
    }
}
