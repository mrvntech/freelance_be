package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.application.CreateApplicationBody;
import com.example.freelance_be.dto.request.application.UpdateApplicationRequestBody;
import com.example.freelance_be.dto.response.applications.GetAllApplication;
import com.example.freelance_be.dto.response.applications.UpdateApplicationResponseBody;
import com.example.freelance_be.entities.Application;
import com.example.freelance_be.services.application.impl.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/applications")
@RestController
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("")
    public boolean createApplication(@RequestBody CreateApplicationBody body){
        return applicationService.createApplication(body);
    }

    @GetMapping("")
    public ResponseEntity<List<GetAllApplication>> getAllApplication(@RequestParam Map<String, String> query){
        return ResponseEntity.ok().body(applicationService.getAllApplication(query));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateApplicationResponseBody> updateApplication(@PathVariable Long id, @RequestBody UpdateApplicationRequestBody requestBody){
        return ResponseEntity.ok().body(applicationService.updateApplication(id, requestBody));
    }
}
