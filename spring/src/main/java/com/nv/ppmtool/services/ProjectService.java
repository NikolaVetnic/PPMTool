package com.nv.ppmtool.services;

import com.nv.ppmtool.domain.Backlog;
import com.nv.ppmtool.domain.Project;
import com.nv.ppmtool.domain.User;
import com.nv.ppmtool.exceptions.ProjectIdException;
import com.nv.ppmtool.exceptions.ProjectNotFoundException;
import com.nv.ppmtool.repositories.BacklogRepository;
import com.nv.ppmtool.repositories.ProjectRepository;
import com.nv.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ProjectService {


    @Autowired private BacklogRepository backlogRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private UserRepository userRepository;


    public Project saveOrUpdateProject(Project project, String username) {

        try {

            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
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


    public Project findProjectByIdentifier(String projectIdentifier, String username) {

        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        if (project == null)
            throw new ProjectIdException(
                    String.format("Project with ID '%s' doesn't exist",
                            projectIdentifier.toUpperCase()));

        if (!project.getProjectLeader().equals(username))
            throw new ProjectNotFoundException(
                    String.format("Project with ID '%s' does not belong to user %s",
                            projectIdentifier.toUpperCase(), username));

        return project;
    }


    public Iterable<Project> findAllProjects(String username) {
        return projectRepository.findAllByProjectLeader(username);
    }


    public void deleteProjectByIdentifier(String projectIdentifier, String username) {
        projectRepository.delete(findProjectByIdentifier(projectIdentifier.toUpperCase(), username));
    }
}
