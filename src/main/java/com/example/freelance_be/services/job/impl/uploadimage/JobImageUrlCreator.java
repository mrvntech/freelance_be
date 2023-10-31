package com.example.freelance_be.services.job.impl.uploadimage;

import org.springframework.stereotype.Component;

@Component
public class JobImageUrlCreator {
    public String createImageUrl(Long jobId, String originalName){
        return "job_" + jobId + "_" + originalName;
    }
}
