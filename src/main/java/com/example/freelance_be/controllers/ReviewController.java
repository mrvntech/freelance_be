package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.review.CreateReviewRequestBody;
import com.example.freelance_be.services.review.impl.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/reviews")
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("")
    public boolean createReview(@RequestBody CreateReviewRequestBody requestBody){
        return reviewService.createReview(requestBody);
    }

    @GetMapping("")
    public boolean getAllReview(@RequestParam Map<String, String> query){
        return true;
    }
}
