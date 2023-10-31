package com.example.freelance_be.services.job.impl.applyjob;

import com.example.freelance_be.entities.Job;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.job.IApplyJobService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplyJobService implements IApplyJobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    public ApplyJobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void applyJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new BadRequestException("job do not exited"));
        List<User> appliers = job.getAppliers();
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User authUser = userRepository.findByUsername(username).orElseThrow(() -> new AuthenticationException("authentication error"));
        appliers.add(authUser);
        job.setAppliers(appliers);
        jobRepository.save(job);
    }
}
