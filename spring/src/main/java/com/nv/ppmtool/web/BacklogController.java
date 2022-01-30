package com.nv.ppmtool.web;

import com.nv.ppmtool.domain.ProjectTask;
import com.nv.ppmtool.services.MapValidationErrorService;
import com.nv.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/backlog")
public class BacklogController {


    @Autowired private MapValidationErrorService mapValidationErrorService;
    @Autowired private ProjectTaskService projectTaskService;


    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskToBacklog(
            @Valid @RequestBody ProjectTask projectTask,
            BindingResult result,
            @PathVariable String backlog_id) {

        if (result.hasErrors())
            return mapValidationErrorService.mapValidationService(result);

        ProjectTask addedProjectTask = projectTaskService.addProjectTask(backlog_id.toUpperCase(), projectTask);

        return new ResponseEntity<ProjectTask>(addedProjectTask,HttpStatus.CREATED);
    }
}
