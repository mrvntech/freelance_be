package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.taskcomment.CreateTaskCommentRequestBody;
import com.example.freelance_be.dto.response.taskcomment.GetAllTaskCommentResponseBody;
import com.example.freelance_be.services.taskcomment.impl.TaskCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/task-comment")
public class TaskCommentController {
    private final TaskCommentService taskCommentService;

    public TaskCommentController(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }

    @PostMapping("")
    public boolean createTaskComment(@RequestBody CreateTaskCommentRequestBody requestBody){
        taskCommentService.createTaskComment(requestBody);
        return true;
    }

    @GetMapping("")
    public ResponseEntity<GetAllTaskCommentResponseBody> getAllTaskComment(@RequestParam Map<String, String> query){
        return ResponseEntity.ok().body(taskCommentService.getAllTaskComment(query));
    }
}
