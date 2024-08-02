package fon.mas.novica.micronaut.service.impl;

import fon.mas.novica.micronaut.model.dto.project.CreateProjectCmd;
import fon.mas.novica.micronaut.model.dto.project.ProjectDetails;
import fon.mas.novica.micronaut.model.dto.project.ProjectInfo;
import fon.mas.novica.micronaut.model.dto.task.CreateTaskCmd;
import fon.mas.novica.micronaut.model.dto.task.TaskInfo;
import fon.mas.novica.micronaut.model.enums.Status;
import fon.mas.novica.micronaut.service.ProjectsService;

import java.util.List;

public class ProjectsServiceImpl implements ProjectsService {
    @Override
    public ProjectInfo createBlankProject(CreateProjectCmd project) {
        return null;
    }

    @Override
    public List<ProjectInfo> findAllProjects() {
        return null;
    }

    @Override
    public List<ProjectInfo> findActiveProjects() {
        return null;
    }

    @Override
    public TaskInfo addTask(Long id, CreateTaskCmd cmd) {
        return null;
    }

    @Override
    public ProjectDetails showProjectDetails(Long id) {
        return null;
    }

    @Override
    public TaskInfo setTaskStatus(Long id, Status status) {
        return null;
    }
}
