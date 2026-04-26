package com.example.reviewsservice.service;

import com.example.reviewsservice.DTO.ReviewRequestDTO;
import com.example.reviewsservice.response.BookResponse; // تأكد من عمل import للـ DTO الجديد
import com.example.reviewsservice.client.CatalogClient;
import com.example.reviewsservice.entity.Review;
import com.example.reviewsservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;
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
        // 1. التأكد إن الكتاب موجود في الـ Catalog عن طريق الـ Feign Client
        try {
            // بننادي على الميثود الجديدة اللي بترجع بيانات الكتاب
            BookResponse book = catalogClient.getBookById(dto.bookId());
            
            // لو الـ Catalog رجع null ده معناه إن الكتاب مش موجود
            if (book == null) {
                throw new RuntimeException("Book with ID " + dto.bookId() + " not found in Catalog!");
            }
        } catch (Exception e) {
            // لو الـ Catalog Service واقعة أو حصل مشكلة في الربط (Feign Exception)
            throw new RuntimeException("Catalog Service is unavailable or book does not exist!");
        }

        // 2. تحويل الـ DTO لـ Entity وحفظها في قاعدة بيانات الـ Reviews
        Review review = new Review();
        review.setComment(dto.comment());
        review.setRating(dto.rating());
        review.setBookId(dto.bookId());
        review.setUserName(dto.userName());

        return reviewRepository.save(review);
    }
}