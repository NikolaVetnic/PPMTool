package com.nv.ppmtool.services;

import com.nv.ppmtool.domain.Backlog;
import com.nv.ppmtool.domain.ProjectTask;
import com.nv.ppmtool.exceptions.ProjectNotFoundException;
import com.nv.ppmtool.repositories.BacklogRepository;
import com.nv.ppmtool.repositories.ProjectRepository;
import com.nv.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectTaskService {


    @Autowired private BacklogRepository backlogRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private ProjectTaskRepository projectTaskRepository;
    @Autowired private ProjectService projectService;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {

        // ProjectTasks to be added to a specific project, project != null -> Backlog exists
        Backlog backlog = projectService
                .findProjectByIdentifier(projectIdentifier.toUpperCase(), username).getBacklog();

        // set the Backlog to ProjectTask
        projectTask.setBacklog(backlog);

        // update the Backlog sequence
        backlog.incPTSequence();

        // add sequence to ProjectTask (we want our project sequence to be like this: IDPRO-1, IDPRO-2...)
        projectTask.setProjectSequence(projectIdentifier + "-" + backlog.getProjectTaskSequence());
        projectTask.setProjectIdentifier(projectIdentifier);

        // initial priority when priority is null
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0)
            projectTask.setPriority(3);

        // initial status when status is null
        if (projectTask.getStatus() == null || projectTask.getStatus() == "")
            projectTask.setStatus("TO_DO");

        return projectTaskRepository.save(projectTask);
    }


    public Iterable<ProjectTask> findBacklogById(String id, String username) {

        projectService.findProjectByIdentifier(id, username);

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }


    public ProjectTask findProjectTaskByProjectSequence(String backlog_id, String pt_id) {

        // make sure we are searching on an existing backlog
        if (backlogRepository.findByProjectIdentifier(backlog_id) == null)
            throw new ProjectNotFoundException("Project with id '" + backlog_id.toUpperCase() + "' not found");

        // make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

        if (projectTask == null)
            throw new ProjectNotFoundException("ProjectTask with id '" + pt_id.toUpperCase() + "' not found");

        // make sure that the backlog/project id in the path corresponds to the right project
        if (!projectTask.getProjectIdentifier().equals(backlog_id.toUpperCase()))
            throw new ProjectNotFoundException(String.format(
                    "ProjectTask with id '%s' does not belong to Project with id '%s'",
                    pt_id.toUpperCase(), backlog_id.toUpperCase(Locale.ROOT)));

        return projectTask;
    }


    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id) {

        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, pt_id);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }


    public void deleteProjectTaskByProjectSequence(String backlog_id, String pt_id) {

        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, pt_id);

        projectTaskRepository.delete(projectTask);
    }
}
