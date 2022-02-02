package com.nv.ppmtool.services;

import com.nv.ppmtool.domain.Backlog;
import com.nv.ppmtool.domain.Project;
import com.nv.ppmtool.domain.ProjectTask;
import com.nv.ppmtool.exceptions.ProjectNotFoundException;
import com.nv.ppmtool.repositories.BacklogRepository;
import com.nv.ppmtool.repositories.ProjectRepository;
import com.nv.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {


    @Autowired private BacklogRepository backlogRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private ProjectTaskRepository projectTaskRepository;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        // exceptions - project not found

        try {
            // ProjectTasks to be added to a specific project, project != null -> Backlog exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

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

        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id) {

        if (projectRepository.findByProjectIdentifier(id.toUpperCase()) == null)
            throw new ProjectNotFoundException("Project not found");

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
}
