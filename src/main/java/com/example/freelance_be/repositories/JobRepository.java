package com.example.freelance_be.repositories;

import com.example.freelance_be.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
