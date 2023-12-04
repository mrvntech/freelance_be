package com.example.freelance_be.services.job.impl.createjob;

import com.example.freelance_be.domain.JobStatus;
import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.entities.*;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.*;
import com.example.freelance_be.services.job.ICreateJobService;
import com.example.freelance_be.services.minio.iml.MinioService;
import io.minio.errors.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CreateJobService implements ICreateJobService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RequestBodyConverter requestBodyConverter;
    private final JobRepository jobRepository;
    private final LevelRepository levelRepository;
    private final WorkingTypeRepository workingTypeRepository;
    private final MinioService minioService;

    public CreateJobService(UserRepository userRepository, CategoryRepository categoryRepository, RequestBodyConverter requestBodyConverter, JobRepository jobRepository, LevelRepository levelRepository, WorkingTypeRepository workingTypeRepository, MinioService minioService) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.requestBodyConverter = requestBodyConverter;
        this.jobRepository = jobRepository;
        this.levelRepository = levelRepository;
        this.workingTypeRepository = workingTypeRepository;
        this.minioService = minioService;
    }

    public Job createJob(CreateJobRequestBody requestBody) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ParseException {
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
        job.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse(requestBody.getDueDate()));
        job.setWorkingType(workingType);
//        String imageUrl = minioService.uploadImage(file);
//        job.setImageUrl(imageUrl);
        jobRepository.save(job);
        return job;
    }
}
