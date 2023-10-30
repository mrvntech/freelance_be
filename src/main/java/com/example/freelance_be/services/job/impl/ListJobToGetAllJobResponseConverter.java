package com.example.freelance_be.services.job.impl;

import com.example.freelance_be.dto.response.job.GetAllJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListJobToGetAllJobResponseConverter extends Converter<List<Job>, GetAllJobResponseBody> {
    private final ModelMapper modelMapper;
    public ListJobToGetAllJobResponseConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GetAllJobResponseBody convert(List<Job> jobs) {
        GetAllJobResponseBody responseBody = new GetAllJobResponseBody();
        responseBody.setJobs(jobs.stream().map((job)-> {
            return modelMapper.map(job, GetAllJobResponseBody.Job.class);
        }).collect(Collectors.toList()));
        return responseBody;
    }
}
