package com.example.freelance_be.services.userjob.impl;

import com.example.freelance_be.dto.response.myjob.GetAllMyJobResponseBody;
import com.example.freelance_be.dto.response.myjob.GetMyJobResponseBody;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.services.userjob.IUserJobService;
import org.modelmapper.ModelMapper;

public class UserJobService implements IUserJobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    public UserJobService(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GetMyJobResponseBody getMyJob(Long id) {
        return null;
    }

    @Override
    public GetAllMyJobResponseBody getAllMyJob() {
        return null;
    }
}
