package com.example.freelance_be.repositories;

import com.example.freelance_be.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTaskByUserId(Long id);
}
