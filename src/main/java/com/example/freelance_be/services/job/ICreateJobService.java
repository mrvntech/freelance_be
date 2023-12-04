package com.example.freelance_be.services.job;

import com.example.freelance_be.dto.request.job.CreateJobRequestBody;
import com.example.freelance_be.entities.Job;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface ICreateJobService {
    Job createJob(CreateJobRequestBody requestBody) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ParseException;
}
