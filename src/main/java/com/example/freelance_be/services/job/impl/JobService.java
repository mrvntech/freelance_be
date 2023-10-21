package com.example.freelance_be.services.job.impl;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.job.IJobService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService implements IJobService {
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    public JobService(UserRepository userRepository, JobRepository jobRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateJobResponseBody createJob(CreateJobRequestBody requestBody) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<User> user = userRepository.findById(Long.valueOf(1));
//        if(user.isEmpty()) throw new RuntimeException();
//        Job job = modelMapper.map(requestBody, Job.class);
//        job.setUser(user.get());
//        jobRepository.save(job);
//        return modelMapper.map(job, CreateJobResponseBody.class);
        return null;
    }

    @Override
    public GetAllJobResponseBody getAllJob() {
        return null;
    }

    @Override
    public GetJobResponseBody getJob(Long id) {
        return null;
    }
}
