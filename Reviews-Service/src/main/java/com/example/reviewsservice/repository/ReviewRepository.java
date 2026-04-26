package com.example.reviewsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.reviewsservice.entity.Review;
import java.util.List; // لازم تعمل Import للـ List

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
   
    List<Review> findByBookId(Long bookId);
}