package com.example.freelance_be.services.job.impl;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Category;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.CategoryRepository;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.job.IJobService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class JobService implements IJobService {
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public JobService(UserRepository userRepository, JobRepository jobRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateJobResponseBody createJob(CreateJobRequestBody requestBody) {
        AtomicReference<CreateJobResponseBody> responseBody = new AtomicReference<>(null);
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresentOrElse((existedUser) -> {
            Job job = modelMapper.map(requestBody, Job.class);
            job.setCustomer(existedUser);
            Optional<Category> category = categoryRepository.findById(requestBody.getCategoryId());
            category.ifPresentOrElse(job::setCategory, () -> {
                throw new BadRequestException("Category do not existed");
            });
            jobRepository.save(job);

            CreateJobResponseBody createJobResponseBody = modelMapper.map(job, CreateJobResponseBody.class);
            responseBody.set(createJobResponseBody);
        }, () -> {
            throw new AuthenticationException("Authentication Error");
        });
        return responseBody.get();
    }

    @Override
    public GetAllJobResponseBody getAllJob(Map<String, String> allParams) {
        List<Job> jobs = jobRepository.findAll();
        GetAllJobResponseBody responseBody = new GetAllJobResponseBody();
        responseBody.setJobs(jobs.stream().map((job)-> {
            return modelMapper.map(job, com.example.freelance_be.services.job.domain.Job.class);
        }).toList());
        return responseBody;
    }

    @Override
    public String uploadImageUrl(String imageUrl) {
        return null;
    }


    @Override
    public GetJobResponseBody getJob(Map<String, String> allParams) {
        Optional<Job> job = jobRepository.findById(Long.valueOf(allParams.get("id")));
        if(job.isEmpty()) throw new BadRequestException("job is not existed");
        return modelMapper.map(job.get(), GetJobResponseBody.class);
    }
}
