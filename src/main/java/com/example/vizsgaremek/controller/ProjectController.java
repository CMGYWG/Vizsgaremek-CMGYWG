package com.example.vizsgaremek.controller;

import com.example.vizsgaremek.model.Project;
import com.example.vizsgaremek.model.dto.ProjectDTO;
import com.example.vizsgaremek.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(
            summary = "Find all projects",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Responds with a list of Project entities."),
            }
    )
    @GetMapping
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @Operation(
            summary = "Post a project",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Project has been successfully posted."),
                    @ApiResponse(responseCode = "400", description = "Wrong project object has been posted.")
            }
    )
    @PostMapping
    public void save(@Valid @RequestBody ProjectDTO projectDTO) {
        projectService.save(projectDTO);
    }

    @Operation(
            summary = "Find project by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Project has been found."),
                    @ApiResponse(responseCode = "500", description = "No value present.")
            }
    )
    @GetMapping("/{id}")
    public Project findById(@PathVariable("id") Long id) {
        return projectService.findById(id);
    }

    @Operation(
            summary = "Delete project by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Project has been deleted."),
                    @ApiResponse(responseCode = "500", description = "No value present.")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        projectService.deleteById(id);
    }

    @Operation(
            summary = "Update project by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Project has been updated."),
                    @ApiResponse(responseCode = "500", description = "No value present.")
            }
    )
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id,@Valid @RequestBody ProjectDTO projectDTO) {
        projectService.update(id, projectDTO);
    }
}
