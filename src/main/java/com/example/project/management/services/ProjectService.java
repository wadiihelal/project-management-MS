package com.example.project.management.services;

import com.example.project.management.entities.Project;
import com.example.project.management.entities.Task;

import java.util.List;

public interface ProjectService {
    /**
     * Creates a new project.
     *
     * @param project the project to create
     * @return the created project with generated ID
     */
    Project createProject(Project project);

    /**
     * Retrieves a project by its ID.
     *
     * @param id the ID of the project to retrieve
     * @return the project with the specified ID
     * @throws RuntimeException if the project is not found
     */
    Project getProjectById(Long id);

    /**
     * Adds a task to a specified project.
     *
     * @param projectId the ID of the project to which the task will be added
     * @param task      the task to add to the project
     * @return the added task
     * @throws RuntimeException if the project is not found
     */
    Task addTaskToProject(Long projectId, Task task);

    /**
     * Lists all tasks associated with a specified project.
     *
     * @param projectId the ID of the project for which to list tasks
     * @return a list of tasks associated with the project
     */
    List<Task> listTasksByProjectId(Long projectId);
}
