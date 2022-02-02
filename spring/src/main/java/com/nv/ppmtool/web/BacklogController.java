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
import javax.xml.ws.Response;
import java.util.List;

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


    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {

        return projectTaskService.findBacklogById(backlog_id.toUpperCase());
    }


    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id) {

        ProjectTask projectTask = projectTaskService.findProjectTaskByProjectSequence(backlog_id, pt_id);

        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }


    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(
            @Valid @RequestBody ProjectTask projectTask, BindingResult result,
            @PathVariable String backlog_id, @PathVariable String pt_id) {

        if (result.hasErrors())
            return mapValidationErrorService.mapValidationService(result);

        ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask, backlog_id, pt_id);

        return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);
    }


    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id) {

        projectTaskService.deleteProjectTaskByProjectSequence(backlog_id, pt_id);

        return new ResponseEntity<String>(
                "ProjectTask '" + pt_id.toUpperCase() + "' deleted successfully", HttpStatus.OK);
    }
}
