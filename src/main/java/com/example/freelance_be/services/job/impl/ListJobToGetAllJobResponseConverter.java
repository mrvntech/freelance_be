package com.example.freelance_be.services.job.impl;

import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.services.job.impl.getImageUrl.GetImageUrlService;
import com.example.freelance_be.utils.Converter;
import io.minio.errors.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListJobToGetAllJobResponseConverter extends Converter<List<Job>, GetAllJobResponseBody> {
    private final ModelMapper modelMapper;
    public final GetImageUrlService getImageUrlService;
    public ListJobToGetAllJobResponseConverter(ModelMapper modelMapper, GetImageUrlService getImageUrlService) {
        this.modelMapper = modelMapper;
        this.getImageUrlService = getImageUrlService;
    }
    @Override
    public GetAllJobResponseBody convert(List<Job> jobs) {
        GetAllJobResponseBody responseBody = new GetAllJobResponseBody();
        responseBody.setJobs(jobs.stream().map((job)-> {
            GetAllJobResponseBody.Job responseJob = modelMapper.map(job, GetAllJobResponseBody.Job.class);
            responseJob.setCategoryName(job.getCategory().getName());
            responseJob.setOwner(new GetAllJobResponseBody.Owner(1L, "phucnq", ""));
            return responseJob;
        }).collect(Collectors.toList()));
        return responseBody;
    }
}
