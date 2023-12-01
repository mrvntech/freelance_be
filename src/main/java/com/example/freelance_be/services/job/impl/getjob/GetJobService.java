package com.example.freelance_be.services.job.impl.getjob;

import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.services.job.IGetJobService;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class GetJobService implements IGetJobService {
    private final JobRepository jobRepository;

    public GetJobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job getJob(Long id) {
//        return jobRepository.findJob(id, "FE").orElseThrow(() -> new BadRequestException("job do not existed"));
        return null;
    }
}
