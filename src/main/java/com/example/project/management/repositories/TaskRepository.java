package com.example.project.management.repositories;

import com.example.project.management.entities.Project;
import com.example.project.management.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Finds task by project.
     *
     * @param project
     * @return
     */
    List<Task> findByProject(Project project);
}
