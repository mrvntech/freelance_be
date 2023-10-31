package com.example.freelance_be.services.job.impl.getImageUrl;

import com.example.freelance_be.entities.Job;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.services.job.IGetImageUrlService;
import com.example.freelance_be.utils.MinioConfig;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
@Component
public class GetImageUrlService implements IGetImageUrlService {
    private final JobRepository jobRepository;
    private final MinioClient minioClient;

    public GetImageUrlService(JobRepository jobRepository, MinioClient minioClient) {
        this.jobRepository = jobRepository;
        this.minioClient = minioClient;
    }

    @Override
    public String getImageUrl(Long jobId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new BadRequestException("job do not existed"));
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(MinioConfig.bucket)
                        .object(job.getImageUrl())
                        .method(Method.GET)
                        .expiry(60, TimeUnit.SECONDS)
                        .build()
        );
    }
}
