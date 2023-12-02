package com.example.freelance_be.services.usertask.impl;

import com.example.freelance_be.dto.response.task.GetAllTaskResponseBody;
import com.example.freelance_be.entities.Task;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.repositories.TaskRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.usertask.IUserTaskService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class UserTaskService implements IUserTaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserTaskService(TaskRepository taskRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GetAllTaskResponseBody getAllTask() {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User authUser = userRepository.findByEmail(username).orElseThrow(() -> new AuthenticationException("authentication error"));
        List<Task> taskList = taskRepository.findTaskByUserId(authUser.getId());
        GetAllTaskResponseBody responseBody = new GetAllTaskResponseBody();
        responseBody.setTasks(taskList.stream().map(task -> modelMapper.map(task, GetAllTaskResponseBody.Task.class)).toList());
        return responseBody;
    }
}
