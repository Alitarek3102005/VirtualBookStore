package com.example.reviewsservice.controller;

import com.example.reviewsservice.entity.Review;
import com.example.reviewsservice.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/reviews") 
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

   
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

 
    @PostMapping
    public Review addReview(@Valid @RequestBody Review review) {
       
        return reviewService.addReview(review);
    }
}