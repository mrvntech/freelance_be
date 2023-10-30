package com.example.freelance_be.services.job.impl.createjob;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.entities.Category;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.CategoryRepository;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.job.ICreateJobService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class CreateJobService implements ICreateJobService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RequestBodyConverter requestBodyConverter;
    private final JobRepository jobRepository;

    public CreateJobService(UserRepository userRepository, CategoryRepository categoryRepository, RequestBodyConverter requestBodyConverter, JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.requestBodyConverter = requestBodyConverter;
        this.jobRepository = jobRepository;
    }

    public Job createJob(CreateJobRequestBody requestBody) {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User authUser = userRepository.findByUsername(username).orElseThrow(() -> new AuthenticationException("authentication error"));
        Job job = requestBodyConverter.convert(requestBody);
        Category category = categoryRepository.findById(requestBody.getCategoryId()).orElseThrow(() -> new BadRequestException("category do not existed"));
        job.setCustomer(authUser);
        job.setCategory(category);
        jobRepository.save(job);
        return job;
    }
}
