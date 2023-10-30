package com.example.freelance_be.services.job.impl.getalljob;

import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.services.job.IGetAllJobService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class GetAllJobService implements IGetAllJobService {
    private final JobRepository jobRepository;

    public GetAllJobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJob(Map<String, String> allParams) {
        SearchProperties searchProperties = new SearchProperties(allParams);
        return jobRepository.findAll(searchProperties.getJobSpecification());
    }
}
