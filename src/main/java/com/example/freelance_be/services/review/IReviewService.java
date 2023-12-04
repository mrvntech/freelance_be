package com.example.freelance_be.services.review;

import com.example.freelance_be.dto.request.review.CreateReviewRequestBody;

public interface IReviewService {
    boolean createReview(CreateReviewRequestBody requestBody);

}
