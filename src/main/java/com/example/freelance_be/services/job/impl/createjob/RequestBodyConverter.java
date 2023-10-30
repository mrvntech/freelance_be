package com.example.freelance_be.services.job.impl.createjob;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.entities.Job;
import com.example.freelance_be.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RequestBodyConverter extends Converter<CreateJobRequestBody, Job> {
    private final ModelMapper modelMapper;
    public RequestBodyConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Job convert(CreateJobRequestBody requestBody) {
        Job job = modelMapper.map(requestBody, Job.class);
        System.out.println(job.toString());
        return job;
    }
}
