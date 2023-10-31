package com.example.freelance_be.services.job;

import com.example.freelance_be.dto.request.job.HideFreelancerRequestBody;

public interface IHideFreelancerService {
    boolean hideFreelancer(Long freelancerId, HideFreelancerRequestBody requestBody);
}
