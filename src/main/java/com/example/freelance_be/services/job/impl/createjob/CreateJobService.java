package com.example.freelance_be.services.job.impl.createjob;

import com.example.freelance_be.domain.JobStatus;
import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.entities.*;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.*;
import com.example.freelance_be.services.job.ICreateJobService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateJobService implements ICreateJobService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RequestBodyConverter requestBodyConverter;
    private final JobRepository jobRepository;
    private final LevelRepository levelRepository;
    private final WorkingTypeRepository workingTypeRepository;

    public CreateJobService(UserRepository userRepository, CategoryRepository categoryRepository, RequestBodyConverter requestBodyConverter, JobRepository jobRepository, LevelRepository levelRepository, WorkingTypeRepository workingTypeRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.requestBodyConverter = requestBodyConverter;
        this.jobRepository = jobRepository;
        this.levelRepository = levelRepository;
        this.workingTypeRepository = workingTypeRepository;
    }

    public Job createJob(CreateJobRequestBody requestBody) {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User authUser = userRepository.findByEmail(username).orElseThrow(() -> new AuthenticationException("authentication error"));
        Job job = requestBodyConverter.convert(requestBody);
        job.setCreatedAt(new Date());
        job.setStatus(JobStatus.OPEN.getName());
        Category category = categoryRepository.findById(requestBody.getCategoryId()).orElseThrow(() -> new BadRequestException("category do not existed"));
        Level level = levelRepository.findById(requestBody.getLevelId()).orElseThrow(() -> new BadRequestException("level is not exist"));
        WorkingType workingType = workingTypeRepository.findById(requestBody.getWorkingTypeId()).orElseThrow(() -> new BadRequestException("working type is not exist"));
        job.setOwner(authUser);
        job.setCategory(category);
        job.setLevel(level);
        job.setWorkingType(workingType);
        jobRepository.save(job);
        return job;
    }
}
