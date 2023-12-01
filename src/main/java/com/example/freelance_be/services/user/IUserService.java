package com.example.freelance_be.services.user;

import com.example.freelance_be.dto.request.user.UpdateUserInformationRequestBody;
import com.example.freelance_be.dto.response.user.GetAllUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.GetUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.UpdateUserInformationResponseBody;

import java.text.ParseException;

public interface IUserService {
    void initAdminAccount();
    GetUserInformationResponseBody getUserInformation();
    UpdateUserInformationResponseBody updateUserInformation(UpdateUserInformationRequestBody requestBody) throws ParseException;
    GetAllUserInformationResponseBody getAllUser();
    boolean deleteUser(Long id);
}
