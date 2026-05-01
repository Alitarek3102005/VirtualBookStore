package com.example.reviewsservice.service;

import com.example.reviewsservice.DTO.ReviewRequestDTO;
import com.example.reviewsservice.DTO.updateReviewDTO;
import com.example.reviewsservice.response.BookResponse; // تأكد من عمل import للـ DTO الجديد
import com.example.reviewsservice.client.CatalogClient;
import com.example.reviewsservice.entity.Review;
import com.example.reviewsservice.repository.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service 
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CatalogClient catalogClient; 

    // Constructor Injection
    public ReviewService(ReviewRepository reviewRepository, CatalogClient catalogClient) {
        this.reviewRepository = reviewRepository;
        this.catalogClient = catalogClient;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public Review addReview(ReviewRequestDTO dto) {
        try {
            BookResponse book = catalogClient.getBookById(dto.bookId());
            
            if (book == null) {
                throw new RuntimeException("Book with ID " + dto.bookId() + " not found in Catalog!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Catalog Service is unavailable or book does not exist!");
        }

        Review review = new Review();
        review.setComment(dto.comment());
        review.setRating(dto.rating());
        review.setBookId(dto.bookId());
        review.setUserName(dto.userName());

        return reviewRepository.save(review);
    }
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
    public Review updateReview(Long reviewId, @Valid @RequestBody updateReviewDTO dto) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null) {
            review.setComment(dto.comment());
            review.setRating(dto.rating());
            reviewRepository.save(review);
            return  review;
        }
        return null;
    }

}