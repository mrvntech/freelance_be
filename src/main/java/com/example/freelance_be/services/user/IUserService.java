package com.example.freelance_be.services.user;

import com.example.freelance_be.dto.response.user.GetUserProfileResponseBody;
import com.example.freelance_be.dto.response.user.UpdateUserProfileResponseBody;

public interface IUserService {
    void initAdminAccount();
    GetUserProfileResponseBody getUserProfile();
    UpdateUserProfileResponseBody updateUserProfile();
}
