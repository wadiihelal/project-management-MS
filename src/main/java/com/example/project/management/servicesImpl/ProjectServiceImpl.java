package com.example.project.management.servicesImpl;

import com.example.project.management.entities.Project;
import com.example.project.management.entities.Task;
import com.example.project.management.repositories.ProjectRepository;
import com.example.project.management.repositories.TaskRepository;
import com.example.project.management.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project not found"));
    }


    @Override
    public Task addTaskToProject(Long projectId, Task task) {
        Project project = getProjectById(projectId);
        task.setProject(project);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> listTasksByProjectId(Long projectId) {
        final Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            final Project project = projectOptional.get();
            return taskRepository.findByProject(project);
        } else {
            return new ArrayList<>();
        }

    }
}
