package com.example.freelance_be.services.job.impl;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Category;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.job.IJobService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
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
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
//        Optional<User> user = userRepository.findById(Long.valueOf(1));
        System.out.println(userDetails);
//        if(user.isEmpty()) throw new RuntimeException();
//        Job job = modelMapper.map(requestBody, Job.class);
////        job.setUser(user.get());
//        jobRepository.save(job);
//        return modelMapper.map(job, CreateJobResponseBody.class);
        return null;
    }

    @Override
    public List<Job> getAllJob() {
        Job job = new Job();
        job.setId(1L);
        job.setBudget(1000D);
        job.setInformation("backend service");
        job.setName("react backend");
        Category category = new Category();
        category.setName("backend");
        category.setId(1L);
        job.setImageUrl("https://lh3.googleusercontent.com/a/ACg8ocICwmbdfV7mTWd_kjMQh7qA8dpzOQubx5eJT3OWNadU=s96-c");
        job.setCategory(category);
        User customer = new User();
        customer.setUsername("phucnq@gmail.com");
        job.setCustomer(customer);
        return List.of(job);
    }

    @Override
    public GetJobResponseBody getJob(Long id) {
        return null;
    }
}
