package com.example.freelance_be.services.application.impl;

import com.example.freelance_be.dto.request.application.CreateApplicationBody;
import com.example.freelance_be.entities.Application;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.ApplicationRepository;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.application.IApplicationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ApplicationService implements IApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public boolean createApplication(CreateApplicationBody body) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Jwt)) {
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User authUser = userRepository.findByUsername(username).orElseThrow(() -> new AuthenticationException("authentication error"));
        Application application = new Application();
        Job job = jobRepository.findById(body.getJobId()).orElseThrow(() -> new BadRequestException(""));
        application.setJob(job);
        application.setUser(authUser);
        applicationRepository.save(application);
        return true;
    }

    @Override
    public List<Application> getAllApplication(String role) {
        if (Objects.equals(role, "admin")) {
            System.out.println(applicationRepository.findAll());
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Jwt)) {
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User authUser = userRepository.findByUsername(username).orElseThrow(() -> new AuthenticationException("authentication error"));
        System.out.println(applicationRepository.findByUserId(authUser.getId()));
        return null;
    }
}
