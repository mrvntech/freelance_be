package com.example.freelance_be.services.job.impl;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.services.job.ICreateJobService;
import com.example.freelance_be.services.job.IGetAllJobService;
import com.example.freelance_be.services.job.IGetJobService;
import com.example.freelance_be.services.job.IJobService;
import com.example.freelance_be.services.job.impl.createjob.CreateJobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobService implements IJobService {
    private final ICreateJobService createJobService;
    private final IGetAllJobService getAllJobService;
    private final IGetJobService getJobService;
    private final JobToCreateJobResponseConverter jobToCreateJobResponseConverter;
    private final ListJobToGetAllJobResponseConverter listJobToGetAllJobResponseConverter;
    private final JobToGetJobResponseConverter jobToGetJobResponseConverter;
    public JobService(CreateJobService createJobService, IGetAllJobService getAllJobService, IGetJobService getJobService, JobToCreateJobResponseConverter jobToCreateJobResponseConverter, ListJobToGetAllJobResponseConverter listJobToGetAllJobResponseConverter, JobToGetJobResponseConverter jobToGetJobResponseConverter) {
        this.createJobService = createJobService;
        this.getAllJobService = getAllJobService;
        this.getJobService = getJobService;
        this.jobToCreateJobResponseConverter = jobToCreateJobResponseConverter;
        this.listJobToGetAllJobResponseConverter = listJobToGetAllJobResponseConverter;
        this.jobToGetJobResponseConverter = jobToGetJobResponseConverter;
    }

    @Override
    public CreateJobResponseBody createJob(CreateJobRequestBody requestBody) {
        Job job = createJobService.createJob(requestBody);
        return jobToCreateJobResponseConverter.convert(job);
    }
    @Override
    public GetAllJobResponseBody getAllJob(Map<String, String> allParams) {
        List<Job> jobs = getAllJobService.getAllJob(allParams);
        return listJobToGetAllJobResponseConverter.convert(jobs);
    }
    @Override
    public String uploadImageUrl(String imageUrl) {
        return null;
    }
    @Override
    public GetJobResponseBody getJob(Map<String, String> allParams) {
        Job job = getJobService.getJob(Long.valueOf(allParams.get("id")));
        return jobToGetJobResponseConverter.convert(job);
    }
}
