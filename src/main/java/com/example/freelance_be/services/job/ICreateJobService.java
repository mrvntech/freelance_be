package com.example.freelance_be.services.job;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.entities.Job;

public interface ICreateJobService {
    Job createJob(CreateJobRequestBody requestBody);
}
