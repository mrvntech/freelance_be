package com.example.freelance_be.services.application.impl;

import com.example.freelance_be.dto.request.application.CreateApplicationBody;
import com.example.freelance_be.dto.request.application.UpdateApplicationRequestBody;
import com.example.freelance_be.dto.response.applications.GetAllApplication;
import com.example.freelance_be.dto.response.applications.UpdateApplicationResponseBody;
import com.example.freelance_be.entities.*;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.*;
import com.example.freelance_be.services.application.IApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApplicationService implements IApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ProfileRepository profileRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository, JobRepository jobRepository, ProfileRepository profileRepository, TaskRepository taskRepository, ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.profileRepository = profileRepository;
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createApplication(CreateApplicationBody body) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Jwt)) {
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User authUser = userRepository.findByEmail(username).orElseThrow(() -> new AuthenticationException("authentication error"));
        Application application = new Application();
        Job job = jobRepository.findById(body.getJobId()).orElseThrow(() -> new BadRequestException(""));
        application.setJob(job);
        application.setUser(authUser);
        Profile profile = profileRepository.findById(body.getProfileId()).orElseThrow(() -> new BadRequestException("profile do not exist"));
        application.setProfile(profile);
        application.setStatus("APPLIED");
        applicationRepository.save(application);
        return true;
    }

    @Override
    public List<GetAllApplication> getAllApplication( Map<String, String> query) {
        if(query.get("jobId") != null){
            Long jobId = Long.valueOf(query.get("jobId"));
            List<Application> applications = applicationRepository.findByJobId(jobId);
            return applications.stream().map(application -> modelMapper.map(application, GetAllApplication.class)).toList();
        }
        if(query.get("userId")!=null){
            Long userId = Long.valueOf(query.get("userId"));
            List<Application> applications = applicationRepository.findByUserId(userId);
            return applications.stream().map(application -> modelMapper.map(application, GetAllApplication.class)).toList();
        }
        return null;
    }

    @Override
    public UpdateApplicationResponseBody updateApplication(Long applicationId,UpdateApplicationRequestBody requestBody) {
        Profile profile = profileRepository.findById(requestBody.getProfileId()).orElseThrow(() -> new BadRequestException("profile do not exist"));
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new BadRequestException("application do not exist"));
        application.setProfile(profile);
        if(requestBody.getStatus().equals("START DOING")) {
            applicationRepository.delete(application);
            Task task = new Task();
            task.setJob(application.getJob());
            task.setUser(application.getUser());
            task.setStatus("DOING");
            taskRepository.save(task);
            return null;
        }
        application.setStatus(requestBody.getStatus());
        applicationRepository.save(application);
        UpdateApplicationResponseBody responseBody = new UpdateApplicationResponseBody();
        responseBody.setApplication(modelMapper.map(application, UpdateApplicationResponseBody.Application.class));
        return responseBody;
    }
}
