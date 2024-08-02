package fon.mas.novica.micronaut.rest;

import fon.mas.novica.micronaut.model.dto.project.CreateProjectCmd;
import fon.mas.novica.micronaut.model.dto.task.CreateTaskCmd;
import fon.mas.novica.micronaut.model.enums.Status;
import fon.mas.novica.micronaut.service.ProjectsService;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@Controller("/projects")
@Secured(SecurityRule.IS_ANONYMOUS)
@ExecuteOn(TaskExecutors.BLOCKING)
public class ProjectsController {

    @Inject
    private ProjectsService projectsService;

    @Post
    public Response createProject(@Body CreateProjectCmd cmd){
        return Response.status(Response.Status.CREATED).entity(projectsService.createBlankProject(cmd)).build();
    }
    @Get
    public Response getActiveProjects(){
        return Response.ok(projectsService.findActiveProjects()).build();
    }
    @Get("/all")
    public Response getAllProjects(){
        return Response.ok(projectsService.findAllProjects()).build();
    }
    @Post("/{id}")
    public Response addTask(@PathVariable Long id, @Body CreateTaskCmd cmd){
        return Response.ok(projectsService.addTask(id, cmd)).build();
    }
    @Get("/{id}/info")
    public Response showProjectDetails(@PathVariable Long id){
        return Response.ok(projectsService.showProjectDetails(id)).build();
    }
    @Patch("/tasks/{id}/start")
    public Response setTaskInProgress(@PathVariable Long id){
        return Response.ok(projectsService.setTaskStatus(id, Status.IN_PROGRESS)).build();
    }
    @Patch("/tasks/{id}/done")
    public Response setTaskFinished(@PathVariable Long id){
        return Response.ok(projectsService.setTaskStatus(id, Status.FINISHED)).build();
    }

}
