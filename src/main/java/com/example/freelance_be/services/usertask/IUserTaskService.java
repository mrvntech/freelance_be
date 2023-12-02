package com.example.freelance_be.services.usertask;

import com.example.freelance_be.dto.response.task.GetAllTaskResponseBody;

import java.util.Map;

public interface IUserTaskService {
    GetAllTaskResponseBody getAllTask();
}
