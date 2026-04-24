package com.example.reviewsservice.service;

import com.example.reviewsservice.model.Review;
import com.example.reviewsservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service 
public class ReviewService {

    @Autowired 
    private ReviewRepository reviewRepository;

    // ميثود تجيب كل التقييمات من الداتا بيز
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    
    public Review addReview(Review review) {
      
        return reviewRepository.save(review);
    }
}