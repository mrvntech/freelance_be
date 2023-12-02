package com.example.freelance_be.services.usertask;

import com.example.freelance_be.dto.request.task.UpdateTaskRequestBody;
import com.example.freelance_be.dto.response.task.GetAllTaskResponseBody;
import com.example.freelance_be.dto.response.task.GetTaskResponseBody;
import com.example.freelance_be.dto.response.task.UpdateTaskResponseBody;

import java.util.Map;

public interface IUserTaskService {
    GetAllTaskResponseBody getAllTask();
    GetTaskResponseBody getTask(Long id);
    UpdateTaskResponseBody updateTask(Long id, UpdateTaskRequestBody requestBody);
}
