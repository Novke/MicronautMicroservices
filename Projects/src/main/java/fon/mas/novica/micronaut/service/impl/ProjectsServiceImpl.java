package fon.mas.novica.micronaut.service.impl;

import fon.mas.novica.micronaut.exception.ProjectNotFoundException;
import fon.mas.novica.micronaut.exception.TaskNotFoundException;
import fon.mas.novica.micronaut.exception.UserNotFoundException;
import fon.mas.novica.micronaut.io.NotificationsServiceClient;
import fon.mas.novica.micronaut.io.UsersServiceClient;
import fon.mas.novica.micronaut.model.dto.notification.NewAssignmentNotif;
import fon.mas.novica.micronaut.model.dto.notification.TaskCompletedNotif;
import fon.mas.novica.micronaut.model.dto.project.CreateProjectCmd;
import fon.mas.novica.micronaut.model.dto.project.ProjectDetails;
import fon.mas.novica.micronaut.model.dto.project.ProjectInfo;
import fon.mas.novica.micronaut.model.dto.task.CreateTaskCmd;
import fon.mas.novica.micronaut.model.dto.task.TaskInfo;
import fon.mas.novica.micronaut.model.dto.users.UserInfo;
import fon.mas.novica.micronaut.model.entity.ProjectEntity;
import fon.mas.novica.micronaut.model.entity.TaskEntity;
import fon.mas.novica.micronaut.model.enums.Status;
import fon.mas.novica.micronaut.repository.ProjectsRepository;
import fon.mas.novica.micronaut.repository.TasksRepository;
import fon.mas.novica.micronaut.service.ProjectsService;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Singleton
@Transactional
public class ProjectsServiceImpl implements ProjectsService {

    @Inject
    private ProjectsRepository projectsRepository;
    @Inject
    private TasksRepository tasksRepository;
    @Inject
    private NotificationsServiceClient notificationsService;
    @Inject
    private UsersServiceClient usersService;
    @Inject
    private ModelMapper mapper;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public ProjectInfo createBlankProject(CreateProjectCmd cmd) {
        ProjectEntity project = mapper.map(cmd, ProjectEntity.class);
        project.setCreatedDate(LocalDate.now());

        UserInfo supervisor = findUserById(cmd.supervisorId());
        project.setSupervisorId(supervisor.getId());

        return mapper.map(projectsRepository.save(project), ProjectInfo.class);
    }

    @Override
    public List<ProjectInfo> findAllProjects() {
        return projectsRepository.findAll()
                .stream()
                .map(p -> mapper.map(p, ProjectInfo.class))
                .toList();
    }

    @Override
    public List<ProjectInfo> findActiveProjects() {
        return projectsRepository.findAllByStatusNotEquals(Status.FINISHED)
                .stream()
                .map(p -> mapper.map(p, ProjectInfo.class))
                .toList();
    }

    @Override
    public TaskInfo addTask(Long id, CreateTaskCmd cmd) {
        ProjectEntity project = projectsRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id " + id + " not found!"));

        throwIfUnauthorized(List.of(project.getSupervisorId()));

        UserInfo assignee = findUserById(cmd.assigneeId());
        UserInfo supervisor = findUserById(cmd.supervisorId());

        TaskEntity task = mapper.map(cmd, TaskEntity.class);

        project.addTask(task);
        task.setCreatedDate(LocalDate.now());
        task.setAssigneeId(assignee.getId());
        task.setSupervisorId(supervisor.getId());

        TaskInfo taskInfo = mapper.map(tasksRepository.save(task), TaskInfo.class);
        taskInfo.setAssigneeName(assignee.getFullName());
        taskInfo.setSupervisorName(supervisor.getFullName());

        notifyAssignee(assignee, supervisor, taskInfo);

        return taskInfo;
    }

    @Override
    public ProjectDetails showProjectDetails(Long id) {
        ProjectEntity project = projectsRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id " + id + " not found!"));

        List<TaskInfo> tasks = new ArrayList<>();
        project.getTasks().forEach(te -> {
                    TaskInfo taskInfo = taskEntityToTaskInfo(te);

                    tasks.add(taskInfo);
                }
        );
        ProjectDetails projectDetails = mapper.map(project, ProjectDetails.class);
        projectDetails.setSupervisorName(getUserFullNameSilently(project.getSupervisorId()));
        projectDetails.setTasks(tasks);

        return projectDetails;
    }

    @Override
    public TaskInfo setTaskStatus(Long id, Status status) {
        TaskEntity task = tasksRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task with id %s not found!", id)));

        throwIfUnauthorized(List.of(task.getAssigneeId(), task.getSupervisorId()));

        task.setStatus(status);
        task.setUpdatedDate(LocalDate.now());
        if (status == Status.FINISHED) task.setEndDate(LocalDate.now());

        TaskInfo taskInfo = taskEntityToTaskInfo(tasksRepository.save(task));

        if (status == Status.FINISHED) {
            usersService.increaseTaskCount(task.getAssigneeId());

            if (!Objects.equals(task.getAssigneeId(), task.getSupervisorId())) {
                UserInfo assignee = findUserById(task.getAssigneeId());
                UserInfo supervisor = findUserById(task.getSupervisorId());

                notifyTaskCompleted(assignee, supervisor, taskInfo);
            }
        }

        return taskInfo;
    }

    private void notifyAssignee(UserInfo assignee, UserInfo supervisor, TaskInfo taskInfo){
        NewAssignmentNotif notification = new NewAssignmentNotif(assignee.getFirstName(),
                assignee.getLastName(),
                supervisor.getFullName(),
                assignee.getEmail(),
                taskInfo.getDueDate(),
                taskInfo.getPriority().name(),
                taskInfo.getId());

        notificationsService.notifyAssignee(notification);
    }

    private void notifyTaskCompleted(UserInfo assignee, UserInfo supervisor, TaskInfo taskInfo){
        TaskCompletedNotif notification = new TaskCompletedNotif(
                supervisor.getFirstName(),
                supervisor.getLastName(),
                taskInfo.getId(),
                taskInfo.getTitle(),
                taskInfo.getPriority().toString(),
                supervisor.getEmail(),
                assignee.getFullName(),
                taskInfo.getDueDate());

        notificationsService.notifyTaskCompleted(notification);
    }

    ///////////////////
    //  UTIL FUNKCIJE
    ///////////////////
    private void throwIfUnauthorized(List<Long> ids){
        //TODO
//        if (!usersService.verifyAuthorization(ids)) throw new UnauthorizedActionException();
    }

    private TaskInfo taskEntityToTaskInfo(TaskEntity entity){
        TaskInfo taskInfo = mapper.map(entity, TaskInfo.class);
        taskInfo.setAssigneeName(getUserFullNameSilently(entity.getAssigneeId()));
        taskInfo.setSupervisorName(getUserFullNameSilently(entity.getSupervisorId()));

        return taskInfo;
    }

    private String getUserFullNameSilently(Long id){
        try {
            return findUserById(id).getFullName();
        } catch (Exception ignored){
            return "UNKOWN";
        }
    }

    private UserInfo findUserById(Long id) {
        try {
            return usersService.findUserById(id);
        } catch (HttpClientResponseException ex) {
            log.error("ERROR in findUserById! status code: {}", ex.getStatus().getCode());
            throw ex;
        } catch (Exception ex) {
            throw new UserNotFoundException("cant find user with id " + id, ex);
        }
    }
}
