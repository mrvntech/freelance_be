package com.example.freelance_be.services.user;

import com.example.freelance_be.dto.request.user.UpdateUserInformationRequestBody;
import com.example.freelance_be.dto.response.user.GetAllUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.GetUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.UpdateUserInformationResponseBody;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface IUserService {
    void initAdminAccount();
    GetUserInformationResponseBody getUserInformation();
    UpdateUserInformationResponseBody updateUserInformation(UpdateUserInformationRequestBody requestBody, MultipartFile file) throws ParseException, ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    GetAllUserInformationResponseBody getAllUser();
    boolean deleteUser(Long id);
}
