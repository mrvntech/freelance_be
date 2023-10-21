package com.example.freelance_be.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-task")
public class MyTaskController {
    @GetMapping("")
    public String getMyTask(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return "this is your task";
    }
}
