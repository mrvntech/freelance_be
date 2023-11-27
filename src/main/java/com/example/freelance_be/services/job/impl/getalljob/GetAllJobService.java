package com.example.freelance_be.services.job.impl.getalljob;

import com.example.freelance_be.entities.Job;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.job.IGetAllJobService;
import com.example.freelance_be.utils.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class GetAllJobService implements IGetAllJobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public GetAllJobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Job> getAllJob(Map<String, String> allParams) {
        SearchProperties searchProperties = new SearchProperties(allParams);
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            return jobRepository.findAll(searchProperties.getCategoryId(), null, null, null);
        }
        String email = (String) ((Jwt) principal).getClaims().get("username");
        User user = userRepository.findByUsername(email).orElseThrow(() -> new AuthenticationException("authentication error"));
        if(searchProperties.getRole() == null)throw new BadRequestException("role do not exits");
        if(searchProperties.getRole() == Role.CUSTOMER){
            return jobRepository.findAll(searchProperties.getCategoryId(), user.getId(), null, null);
        }
        return jobRepository.findAll(searchProperties.getCategoryId(), null, user.getId(), null);
    }
}
