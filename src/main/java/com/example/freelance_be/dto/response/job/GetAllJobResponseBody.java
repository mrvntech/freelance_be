package com.example.freelance_be.dto.response.job;

import com.example.freelance_be.services.job.domain.Job;

import java.util.List;

public class GetAllJobResponseBody {
    public List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
