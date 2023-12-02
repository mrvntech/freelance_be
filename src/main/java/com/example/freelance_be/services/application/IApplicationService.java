package com.example.freelance_be.services.application;

import com.example.freelance_be.dto.request.application.CreateApplicationBody;
import com.example.freelance_be.dto.request.application.UpdateApplicationRequestBody;
import com.example.freelance_be.dto.response.applications.GetAllApplication;
import com.example.freelance_be.dto.response.applications.UpdateApplicationResponseBody;
import com.example.freelance_be.entities.Application;

import java.util.List;
import java.util.Map;

public interface IApplicationService {
    boolean createApplication(CreateApplicationBody body);
    List<GetAllApplication> getAllApplication(Map<String, String> query);
    UpdateApplicationResponseBody updateApplication(Long applicationId, UpdateApplicationRequestBody requestBody);
}
