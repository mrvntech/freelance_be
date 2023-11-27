package com.example.freelance_be.services.application;

import com.example.freelance_be.dto.request.application.CreateApplicationBody;
import com.example.freelance_be.entities.Application;

import java.util.List;

public interface IApplicationService {
    boolean createApplication(CreateApplicationBody body);
    List<Application> getAllApplication(String role);
}
