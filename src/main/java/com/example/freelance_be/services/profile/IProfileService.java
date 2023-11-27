package com.example.freelance_be.services.profile;

import com.example.freelance_be.dto.request.profile.CreateProfileRequestBody;
import com.example.freelance_be.dto.request.profile.UpdateProfileRequestBody;
import com.example.freelance_be.dto.response.profile.GetAllProfileResponseBody;
import com.example.freelance_be.dto.response.profile.GetProfileDetailResponseBody;

public interface IProfileService {
    boolean createProfile(CreateProfileRequestBody body);
    boolean updateProfile(UpdateProfileRequestBody body);
    GetAllProfileResponseBody getAllProfile();
    GetProfileDetailResponseBody getProfileDetail(Long id);
}
