package com.example.freelance_be.services.minio.iml;

import com.example.freelance_be.services.minio.IMinioService;
import com.example.freelance_be.utils.MinioConfig;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
@Service
public class MinioService implements IMinioService {
    private final MinioClient minioClient;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String url = new Date().toString()
                + file.getOriginalFilename();

        minioClient.putObject(PutObjectArgs
                .builder()
                .bucket(MinioConfig.bucket)
                .object(url)
                .stream(file.getInputStream(), file.getInputStream().available(), -1)
                .build()
        );
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(MinioConfig.bucket)
                        .object(url).build()
        );
    }
}
