package com.example.freelance_be.services.job.impl;

import com.example.freelance_be.dto.response.job.GetJobResponseBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JobToGetJobResponseConverter extends Converter<Job, GetJobResponseBody> {
    private final ModelMapper modelMapper;

    public JobToGetJobResponseConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GetJobResponseBody convert(Job job) {
        return modelMapper.map(job, GetJobResponseBody.class);
    }
}
