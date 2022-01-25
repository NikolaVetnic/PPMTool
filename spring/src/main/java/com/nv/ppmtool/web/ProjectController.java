package com.nv.ppmtool.web;

import com.nv.ppmtool.domain.Project;
import com.nv.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(
            @Valid @RequestBody Project project, BindingResult result) {

        if (result.hasErrors())
            return new ResponseEntity<Map<String, String>>(
                    result.getFieldErrors().stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                FieldError::getDefaultMessage)),
                    HttpStatus.BAD_REQUEST);

        Project savedProject = projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }
}
