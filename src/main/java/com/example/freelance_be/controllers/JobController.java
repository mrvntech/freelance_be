package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.services.job.impl.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @PostMapping("")
    public ResponseEntity<CreateJobResponseBody> createJob(@RequestBody CreateJobRequestBody requestBody){
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(authority -> System.out.println(authority.getAuthority()));
        return ResponseEntity.ok().body(jobService.createJob(requestBody));
    }

    @GetMapping("")
    public ResponseEntity<List<Job>> getAllJob(){
        return  ResponseEntity.ok().body(jobService.getAllJob());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetJobResponseBody> getJob(@PathVariable Long id){
        return ResponseEntity.ok().body(jobService.getJob(id));
    }
}
