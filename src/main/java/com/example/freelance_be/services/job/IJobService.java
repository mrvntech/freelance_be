package com.example.freelance_be.services.job;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Job;

import java.util.List;

public interface IJobService {
    CreateJobResponseBody createJob(CreateJobRequestBody requestBody);
    List<Job> getAllJob();
    GetJobResponseBody getJob(Long id);
}
