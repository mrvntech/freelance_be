package com.example.freelance_be.services.job.impl;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.dto.request.job.HideFreelancerRequestBody;
import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.services.job.ICreateJobService;
import com.example.freelance_be.services.job.IGetAllJobService;
import com.example.freelance_be.services.job.IGetJobService;
import com.example.freelance_be.services.job.IJobService;
import com.example.freelance_be.services.job.impl.applyjob.ApplyJobService;
import com.example.freelance_be.services.job.impl.createjob.CreateJobService;
import com.example.freelance_be.services.job.impl.getImageUrl.GetImageUrlService;
import com.example.freelance_be.services.job.impl.hidefreelancer.HideFreelancerService;
import com.example.freelance_be.services.job.impl.uploadimage.UploadImageService;
import io.minio.errors.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Service
public class JobService implements IJobService {
    private final ICreateJobService createJobService;
    private final IGetAllJobService getAllJobService;
    private final IGetJobService getJobService;
    private final JobToCreateJobResponseConverter jobToCreateJobResponseConverter;
    private final ListJobToGetAllJobResponseConverter listJobToGetAllJobResponseConverter;
    private final JobToGetJobResponseConverter jobToGetJobResponseConverter;
    private final ApplyJobService applyJobService;
    private final HideFreelancerService hideFreelancerService;
    private final UploadImageService uploadImageService;
    private final GetImageUrlService getImageUrlService;
    public JobService(CreateJobService createJobService, IGetAllJobService getAllJobService, IGetJobService getJobService, JobToCreateJobResponseConverter jobToCreateJobResponseConverter, ListJobToGetAllJobResponseConverter listJobToGetAllJobResponseConverter, JobToGetJobResponseConverter jobToGetJobResponseConverter, ApplyJobService applyJobService, HideFreelancerService hideFreelancerService, UploadImageService uploadImageService, GetImageUrlService getImageUrlService) {
        this.createJobService = createJobService;
        this.getAllJobService = getAllJobService;
        this.getJobService = getJobService;
        this.jobToCreateJobResponseConverter = jobToCreateJobResponseConverter;
        this.listJobToGetAllJobResponseConverter = listJobToGetAllJobResponseConverter;
        this.jobToGetJobResponseConverter = jobToGetJobResponseConverter;
        this.applyJobService = applyJobService;
        this.hideFreelancerService = hideFreelancerService;
        this.uploadImageService = uploadImageService;
        this.getImageUrlService = getImageUrlService;
    }

    @Override
    public CreateJobResponseBody createJob(CreateJobRequestBody requestBody) {
        Job job = createJobService.createJob(requestBody);
        return jobToCreateJobResponseConverter.convert(job);
    }
    @Override
    public GetAllJobResponseBody getAllJob(Map<String, String> allParams) {
        List<Job> jobs = getAllJobService.getAllJob(allParams);
        return listJobToGetAllJobResponseConverter.convert(jobs);
    }
    @Override
    public boolean uploadImageUrl(Long jobId, MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return uploadImageService.uploadImage(jobId, file);
    }

    @Override
    public void applyJob(Long jobId) {
        applyJobService.applyJob(jobId);
    }

    @Override
    public boolean hideFreelancer(Long jobId, HideFreelancerRequestBody requestBody) {
        return hideFreelancerService.hideFreelancer(jobId, requestBody);
    }

    @Override
    public String getJobImageUrl(Long jobId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return getImageUrlService.getImageUrl(jobId);
    }

    @Override
    public GetJobResponseBody getJob(Map<String, String> allParams) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Job job = getJobService.getJob(Long.valueOf(allParams.get("id")));
        GetJobResponseBody responseBody = jobToGetJobResponseConverter.convert(job);
//        if(job.getImageObject() != null){
//            responseBody.setImageUrl(getJobImageUrl(job.getId()));
//        }
        return responseBody;
    }
}
