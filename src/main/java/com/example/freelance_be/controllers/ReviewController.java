package com.example.freelance_be.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/reviews")
@RestController
public class ReviewController {
    @PostMapping("")
    public boolean createReview(){
        return true;
    }

    @GetMapping("")
    public boolean getAllReview(@RequestParam Map<String, String> query){
        return true;
    }
}
