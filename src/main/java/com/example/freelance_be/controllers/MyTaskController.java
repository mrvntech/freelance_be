package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.response.task.GetAllTaskResponseBody;
import com.example.freelance_be.services.usertask.impl.UserTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/my-task")
public class MyTaskController {
    private final UserTaskService userTaskService;

    public MyTaskController(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @GetMapping("")
    public ResponseEntity<GetAllTaskResponseBody> getMyTask(){
        return ResponseEntity.ok().body(userTaskService.getAllTask());
    }
}
