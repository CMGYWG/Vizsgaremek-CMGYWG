package com.example.vizsgaremek.controller;

import com.example.vizsgaremek.model.Project;
import com.example.vizsgaremek.model.dto.ProjectDTO;
import com.example.vizsgaremek.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @PostMapping
    public void save(@RequestBody ProjectDTO projectDTO) {
        projectService.save(projectDTO);
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable("id") Long id) {
        return projectService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        projectService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id,@RequestBody ProjectDTO projectDTO) {
        projectService.update(id, projectDTO);
    }
}
