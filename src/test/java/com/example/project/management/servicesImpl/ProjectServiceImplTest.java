package com.example.project.management.servicesImpl;

import com.example.project.management.ProjectManagementApplication;
import com.example.project.management.entities.Project;
import com.example.project.management.entities.Task;
import com.example.project.management.repositories.ProjectRepository;
import com.example.project.management.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = ProjectManagementApplication.class)
@AutoConfigureMockMvc
class ProjectServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project = new Project();
        project.setProjectId(1L);
        project.setName("Sample Project");

        task = new Task();
        task.setTaskId(1L);
        task.setName("Sample Task");
    }

    @Test
    void testCreateProject() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project createdProject = projectService.createProject(project);

        assertNotNull(createdProject);
        assertEquals(project.getProjectId(), createdProject.getProjectId());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testGetProjectById() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Project foundProject = projectService.getProjectById(1L);

        assertNotNull(foundProject);
        assertEquals(project.getProjectId(), foundProject.getProjectId());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProjectByIdNotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            projectService.getProjectById(1L);
        });

        assertEquals("Project not found", exception.getMessage());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    void testAddTaskToProject() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task addedTask = projectService.addTaskToProject(1L, task);

        assertNotNull(addedTask);
        assertEquals(task.getTaskId(), addedTask.getTaskId());
        assertEquals(project, addedTask.getProject());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testListTasksByProjectId() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(taskRepository.findByProject(project)).thenReturn(List.of(task));

        List<Task> tasks = projectService.listTasksByProjectId(1L);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
        verify(taskRepository, times(1)).findByProject(project);
    }

    @Test
    void testListTasksByProjectIdNotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        List<Task> tasks = projectService.listTasksByProjectId(1L);

        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
        verify(taskRepository, never()).findByProject(any(Project.class));
    }
}
