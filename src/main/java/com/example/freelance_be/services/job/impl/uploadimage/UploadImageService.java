package com.example.freelance_be.services.job.impl.uploadimage;

import com.example.freelance_be.entities.Job;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.JobRepository;
import com.example.freelance_be.services.job.IUploadImageService;
import com.example.freelance_be.utils.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class UploadImageService implements IUploadImageService {
    private final MinioClient minioClient;
    private final JobImageUrlCreator jobImageUrlCreator;
    private final JobRepository jobRepository;

    public UploadImageService(MinioClient minioClient, JobImageUrlCreator jobImageUrlCreator, JobRepository jobRepository) {
        this.minioClient = minioClient;
        this.jobImageUrlCreator = jobImageUrlCreator;
        this.jobRepository = jobRepository;
    }

    @Override
    public boolean uploadImage(Long jobId, MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String imageUrl = jobImageUrlCreator.createImageUrl(jobId, file.getOriginalFilename());
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new BadRequestException("job do not existed"));
//        job.setImageObject(imageUrl);
        jobRepository.save(job);
        minioClient.putObject(PutObjectArgs
                .builder()
                .bucket(MinioConfig.bucket)
                .object(imageUrl)
                .stream(file.getInputStream(), file.getInputStream().available(), -1)
                .build()
        );
        return true;
    }
}
