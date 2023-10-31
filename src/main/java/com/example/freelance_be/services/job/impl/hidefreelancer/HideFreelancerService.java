package com.example.freelance_be.services.job.impl.hidefreelancer;

import com.example.freelance_be.dto.request.job.HideFreelancerRequestBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.job.IHideFreelancerService;
import org.springframework.stereotype.Component;

@Component
public class HideFreelancerService implements IHideFreelancerService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public HideFreelancerService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }
    @Override
    public boolean hideFreelancer(Long jobId, HideFreelancerRequestBody requestBody){
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new BadRequestException("job is not existed"));
        User freelancer = userRepository.findById(requestBody.getFreelancerId()).orElseThrow(() -> new BadRequestException("freelancer do not existed"));
        job.setFreelancer(freelancer);
        jobRepository.save(job);
        return true;
    }
}
