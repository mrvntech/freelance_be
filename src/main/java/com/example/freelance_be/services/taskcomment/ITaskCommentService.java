package com.example.freelance_be.services.taskcomment;

import com.example.freelance_be.dto.request.taskcomment.CreateTaskCommentRequestBody;
import com.example.freelance_be.dto.response.taskcomment.GetAllTaskCommentResponseBody;

import java.util.Map;

public interface ITaskCommentService {
    boolean createTaskComment(CreateTaskCommentRequestBody requestBody);
    GetAllTaskCommentResponseBody getAllTaskComment(Map<String, String> query);
}
