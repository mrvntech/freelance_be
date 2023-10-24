package com.example.freelance_be.services.userjob;

import com.example.freelance_be.dto.response.myjob.GetAllMyJobResponseBody;
import com.example.freelance_be.dto.response.myjob.GetMyJobResponseBody;

public interface IUserJobService {
    GetMyJobResponseBody getMyJob(Long id);
    GetAllMyJobResponseBody getAllMyJob();
}
