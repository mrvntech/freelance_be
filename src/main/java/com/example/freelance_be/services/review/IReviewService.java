package com.example.freelance_be.services.review;

import com.example.freelance_be.dto.request.review.CreateReviewRequestBody;
import com.example.freelance_be.dto.response.review.GetAllReviewResponseBody;

public interface IReviewService {
    boolean createReview(CreateReviewRequestBody requestBody);
//    GetAllReviewResponseBody getAllReview(Long userId);
}
