package com.example.freelance_be.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-jobs")
public class MyJobController {
    @GetMapping("")
    public String getMyJobs(){
        return "get my job success";
    }
}
