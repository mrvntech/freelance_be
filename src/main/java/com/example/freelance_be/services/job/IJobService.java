package com.example.freelance_be.services.job;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Job;

import java.util.List;
import java.util.Map;

public interface IJobService {
    CreateJobResponseBody createJob(CreateJobRequestBody requestBody);
    GetJobResponseBody getJob(Map<String, String> allParams);
    GetAllJobResponseBody getAllJob(Map<String, String> allParams);
    String uploadImageUrl(String imageUrl);
}
