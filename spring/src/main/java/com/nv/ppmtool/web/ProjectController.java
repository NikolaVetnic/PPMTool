package com.nv.ppmtool.web;

import com.nv.ppmtool.domain.Project;
import com.nv.ppmtool.services.MapValidationErrorService;
import com.nv.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired private MapValidationErrorService mapValidationErrorService;
    @Autowired private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        if (result.hasErrors())
            return mapValidationErrorService.mapValidationService(result);

        Project savedProject = projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIdentifier) {

        Project project = projectService.findProjectByIdentifier(projectIdentifier);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
}
