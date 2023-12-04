package com.example.freelance_be.services.review.impl;

import com.example.freelance_be.dto.request.review.CreateReviewRequestBody;
import com.example.freelance_be.dto.response.review.GetAllReviewResponseBody;
import com.example.freelance_be.entities.Review;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.ReviewRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.review.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean createReview(CreateReviewRequestBody requestBody) {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User authUser = userRepository.findByEmail(username).orElseThrow(() -> new AuthenticationException("authentication error"));
        Review review = new Review();
        review.setWriter(authUser);
        User user = userRepository.findById(requestBody.getUserId()).orElseThrow(() -> new BadRequestException("user do not exist"));
        review.setUser(user);
        review.setContent(review.getContent());
        review.setRate(review.getRate());
        review.setCreateAt(new Date());
        reviewRepository.save(review);
        return true;
    }

//    @Override
//    public GetAllReviewResponseBody getAllReview(Long userId) {
//        List<Review> reviews = fi
//        return null;
//    }
}
