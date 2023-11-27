package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.application.CreateApplicationBody;
import com.example.freelance_be.entities.Application;
import com.example.freelance_be.services.application.impl.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Application>> getAllApplication(@RequestParam String role){
        System.out.println(role);
        return ResponseEntity.ok().body(applicationService.getAllApplication(role));
    }
}
