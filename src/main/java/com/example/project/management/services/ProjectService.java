package com.example.project.management.services;

import com.example.project.management.entities.Project;
import com.example.project.management.entities.Task;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);

    Project getProjectById(Long id);

    Task addTaskToProject(Long projectId, Task task);

    List<Task> listTasksByProjectId(Long projectId);
}
