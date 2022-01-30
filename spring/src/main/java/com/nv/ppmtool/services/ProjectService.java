package com.nv.ppmtool.services;

import com.nv.ppmtool.domain.Backlog;
import com.nv.ppmtool.domain.Project;
import com.nv.ppmtool.exceptions.ProjectIdException;
import com.nv.ppmtool.repositories.BacklogRepository;
import com.nv.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {


    @Autowired private BacklogRepository backlogRepository;
    @Autowired private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if (project.getId() == null) {

                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            } else {
                project.setBacklog(backlogRepository.findByProjectIdentifier(
                        project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);

        } catch (Exception e) {
            throw new ProjectIdException(
                    String.format("Project ID '%s' already exists",
                            project.getProjectIdentifier().toUpperCase()));
        }
    }


    public Project findProjectByIdentifier(String projectIdentifier) {

        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        if (project == null)
            throw new ProjectIdException(
                    String.format("Project with ID '%s' doesn't exist",
                            projectIdentifier.toUpperCase()));

        return project;
    }


    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }


    public void deleteProjectByIdentifier(String projectIdentifier) {

        Project project = findProjectByIdentifier(projectIdentifier.toUpperCase());

        if (project == null)
            throw new ProjectIdException(
                    String.format("Cannot delete - project with ID '%s' doesn't exist",
                            projectIdentifier.toUpperCase()));

        projectRepository.delete(project);
    }
}
