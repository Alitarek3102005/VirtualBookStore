package com.example.reviewsservice.controller;

import com.example.reviewsservice.DTO.ReviewRequestDTO;
import com.example.reviewsservice.DTO.updateReviewDTO;
import com.example.reviewsservice.entity.Review;
import com.example.reviewsservice.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable Long bookId) {
        List<Review> reviews = reviewService.getReviewsByBook(bookId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@Valid @RequestBody ReviewRequestDTO dto) {
        
        Review review = reviewService.addReview(dto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id,@Valid @RequestBody updateReviewDTO dto) {
        return  ResponseEntity.ok(reviewService.updateReview(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return  ResponseEntity.noContent().build();
    }
}