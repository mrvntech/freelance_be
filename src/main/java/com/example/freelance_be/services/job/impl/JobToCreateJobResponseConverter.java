package com.example.freelance_be.services.job.impl;

import com.example.freelance_be.dto.response.job.CreateJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JobToCreateJobResponseConverter extends Converter<Job, CreateJobResponseBody> {
    private final ModelMapper modelMapper;

    public JobToCreateJobResponseConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateJobResponseBody convert(Job job) {
        return modelMapper.map(job, CreateJobResponseBody.class);
    }
}
