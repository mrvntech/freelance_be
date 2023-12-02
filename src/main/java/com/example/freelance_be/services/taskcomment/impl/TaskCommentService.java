package com.example.freelance_be.services.taskcomment.impl;

import com.example.freelance_be.dto.request.taskcomment.CreateTaskCommentRequestBody;
import com.example.freelance_be.dto.response.taskcomment.GetAllTaskCommentResponseBody;
import com.example.freelance_be.entities.Task;
import com.example.freelance_be.entities.TaskComment;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.TaskCommentRepository;
import com.example.freelance_be.repositories.TaskRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.taskcomment.ITaskCommentService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class TaskCommentService implements ITaskCommentService {
    private final UserRepository userRepository;
    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskCommentService(UserRepository userRepository, TaskCommentRepository taskCommentRepository, TaskRepository taskRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.taskCommentRepository = taskCommentRepository;
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createTaskComment(CreateTaskCommentRequestBody requestBody) {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User authUser = userRepository.findByEmail(username).orElseThrow(() -> new AuthenticationException("authentication error"));
        TaskComment taskComment = new TaskComment();
        taskComment.setOwner(authUser);
        Task task = taskRepository.findById(requestBody.getTaskId()).orElseThrow(() -> new BadRequestException("task do not exist"));
        taskComment.setTask(task);
        taskComment.setContent(requestBody.getContent());
        taskCommentRepository.save(taskComment);
        return true;
    }

    @Override
    public GetAllTaskCommentResponseBody getAllTaskComment(Map<String, String> query) {
        if(query.get("taskId") != null){
            List<TaskComment> taskComments = taskCommentRepository.findAllByTaskId(Long.valueOf(query.get("taskId")));
            GetAllTaskCommentResponseBody responseBody = new GetAllTaskCommentResponseBody();
            responseBody.setTaskComments(taskComments.stream().map(taskComment -> modelMapper.map(taskComment, GetAllTaskCommentResponseBody.TaskComment.class)).toList());
            return responseBody;
        }
        return null;
    }
}
