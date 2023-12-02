package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.task.UpdateTaskRequestBody;
import com.example.freelance_be.dto.response.task.GetAllTaskResponseBody;
import com.example.freelance_be.dto.response.task.GetTaskResponseBody;
import com.example.freelance_be.dto.response.task.UpdateTaskResponseBody;
import com.example.freelance_be.services.usertask.impl.UserTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponseBody> getTask(@PathVariable Long id){
        return ResponseEntity.ok().body(userTaskService.getTask(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateTaskResponseBody> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequestBody requestBody){
        return ResponseEntity.ok().body(userTaskService.updateTask(id, requestBody));
    }
}
