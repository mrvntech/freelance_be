package com.example.freelance_be.services.job;

import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.entities.Job;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

public interface IGetAllJobService {
    List<Job> getAllJob(Map<String, String> allParams);
}
