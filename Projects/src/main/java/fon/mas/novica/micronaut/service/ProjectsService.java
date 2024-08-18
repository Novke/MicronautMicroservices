package fon.mas.novica.micronaut.service;

import fon.mas.novica.micronaut.model.dto.project.CreateProjectCmd;
import fon.mas.novica.micronaut.model.dto.project.ProjectDetails;
import fon.mas.novica.micronaut.model.dto.project.ProjectInfo;
import fon.mas.novica.micronaut.model.dto.task.CreateTaskCmd;
import fon.mas.novica.micronaut.model.dto.task.TaskInfo;
import fon.mas.novica.micronaut.model.enums.Status;

import java.util.List;

public interface ProjectsService {
    ProjectInfo createBlankProject(CreateProjectCmd project);

    List<ProjectDetails> findAllProjects();

    List<ProjectInfo> findActiveProjects();

    TaskInfo addTask(Long id, CreateTaskCmd cmd);

    ProjectDetails showProjectDetails(Long id);

    TaskInfo setTaskStatus(Long id, Status status);

}
