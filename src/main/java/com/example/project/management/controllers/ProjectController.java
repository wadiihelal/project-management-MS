package com.example.project.management.controllers;

import com.example.project.management.entities.Project;
import com.example.project.management.entities.Task;
import com.example.project.management.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.ok(createdProject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping("/{id}/tasks")
    public ResponseEntity<Task> addTask(@PathVariable Long id, @RequestBody Task task) {
        Task createdTask = projectService.addTaskToProject(id, task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> listTasks(@PathVariable Long id) {
        List<Task> tasks = projectService.listTasksByProjectId(id);
        return ResponseEntity.ok(tasks);
    }
}
