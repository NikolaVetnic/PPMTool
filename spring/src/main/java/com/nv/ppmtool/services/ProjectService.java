package com.nv.ppmtool.services;

import com.nv.ppmtool.domain.Project;
import com.nv.ppmtool.exceptions.ProjectIdException;
import com.nv.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
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
                    String.format("Project ID '%s' doesn't exist",
                            projectIdentifier.toUpperCase()));

        return project;
    }
}
