//package fon.mas.novica.micronaut.service;
//
//import fon.mas.novica.micronaut.model.NewAssignmentNotif;
//import fon.mas.novica.micronaut.model.TaskCompletedNotif;
//import org.slf4j.LoggerFactory;
//
//public record EmailServiceSpring(
//    JavaMailSender mailSender
//) {
//
//    public void notifyAssignment(NewAssignmentNotif notification){
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        log.info("Notification info recieved: " + notification);
//
//        String subject = String.format("%s, you have been assigned a new task!", notification.getFirstName());
//        String content = String.format("""
//                        New task has been assigned to %s %s by the supervisor %s
//                        You are expected to finish the task no later than %s
//
//                        PRIORITY: %s
//                        Check the details for the task using id = %s
//
//                        ====================================================================================================================
//                        THIS EMAIL AND ANY ATTACHMENTS IS INTENDED SOLELY FOR DESIGNATED RECIPIENTS AND MAY CONTAIN CONFIDENTAL INFORMATION.
//                        IF YOU ARE NOT THE INTENDED RECIPIENT (%s) NOTIFY THE ORGANIZATION IMMEDIATELY AND DELETE ALL COPIES.
//                        ANY UNAUTHORIZED REVIEW, USE OR DISCLOSURE IS PROHIBITED.
//                        ====================================================================================================================
//                        """,
//                notification.firstName(),
//                notification.lastName(),
//                notification.supervisor(),
//                notification.dueDate(),
//                notification.priority().toUpperCase(),
//                notification.taskId(),
//                notification.email());
//
//
//        message.setFrom("donotreply");
//        message.setTo(notification.getEmail());
//        message.setSubject(subject);
//        message.setText(content);
//
////        mailSender.send(message);
//        LoggerFactory.getLogger(this.getClass()).info("Mail sent!");
//    }
//
//    public void notifyTaskCompleted(TaskCompletedNotif notification) {
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        log.info("Notification info received: " + notification);
//
//        String subject = String.format("Task \"%s\" Completed", notification.getTaskTitle());
//        String content = String.format("""
//                    Hello %s %s,
//
//                    The task "%s" with ID %d, issued for assignee %s has been marked as completed. As a task supervisor it is in your
//                    responsibility to assure the task has been completed properly.
//
//                    PRIORITY: %s
//                    Due Date: %s
//
//                    Thank you for completing the task on time!
//
//                    ====================================================================================================================
//                    THIS EMAIL AND ANY ATTACHMENTS IS INTENDED SOLELY FOR DESIGNATED RECIPIENTS AND MAY CONTAIN CONFIDENTAL INFORMATION.
//                    IF YOU ARE NOT THE INTENDED RECIPIENT (%s) NOTIFY THE ORGANIZATION IMMEDIATELY AND DELETE ALL COPIES.
//                    ANY UNAUTHORIZED REVIEW, USE OR DISCLOSURE IS PROHIBITED.
//                    ====================================================================================================================
//                    """,
//                notification.firstName(),
//                notification.lastName(),
//                notification.taskTitle(),
//                notification.taskId(),
//                notification.assigneeName(),
//                notification.taskPriority(),
//                notification.dueDate(),
//                notification.email());
//
//        message.setFrom("donotreply");
//        message.setTo(notification.getEmail());
//        message.setSubject(subject);
//        message.setText(content);
//
////        mailSender.send(message);
//        LoggerFactory.getLogger(this.getClass()).info("Mail sent!");
//    }
//
//}
//`