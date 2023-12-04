package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.dto.request.job.HideFreelancerRequestBody;
import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.services.job.impl.JobService;
import io.minio.errors.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @PostMapping("")
    public ResponseEntity<CreateJobResponseBody> createJob(@RequestBody CreateJobRequestBody requestBody) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ParseException {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(authority -> System.out.println(authority.getAuthority()));
        return ResponseEntity.ok().body(jobService.createJob(requestBody));
    }

    @GetMapping("")
    public ResponseEntity<GetAllJobResponseBody> getAllJob(@RequestParam Map<String, String> allParams){
        return ResponseEntity.ok().body(jobService.getAllJob(allParams));
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetJobResponseBody> getJob(@PathVariable Long id) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok().body(jobService.getJob(id));
    }
    @PutMapping("/{id}")
    public boolean updateJobStatus(@PathVariable Long id, @RequestPart("status") String status){
        jobService.updateJobStatus(id, status);
        return true;
    }
//
//    @GetMapping("")
//    public ResponseEntity<GetJobResponseBody> getJob(@RequestParam Map<String, String> allParams) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        return ResponseEntity.ok().body(jobService.getJob(allParams));
//    }
//    @PostMapping("/{id}/apply")
//    public boolean applyJob(@PathVariable Long id){
//        jobService.applyJob(id);
//        return true;
//    }
//    @PostMapping("/{id}/hide")
//    public boolean hideFreelancer(@PathVariable long id, @RequestBody HideFreelancerRequestBody requestBody){
//        return jobService.hideFreelancer(id, requestBody);
//    }
    @PostMapping("/{id}/upload-image")
    public boolean uploadImage(@PathVariable Long id, @RequestParam("image")MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return jobService.uploadImage(id, file);
    }
//    @GetMapping("/{id}/image-url")
//    public String getImageUrl(@PathVariable long id) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        return jobService.getJobImageUrl(id);
//    }
}
