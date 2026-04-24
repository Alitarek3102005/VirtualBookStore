package com.example.reviewsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reviewsservice.model.Review;

@Repository // دي بتعرف Spring إن ده الجزء المسؤول عن التعامل مع الداتا بيز
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    // الميزة هنا إن JpaRepository بتديك كل العمليات الأساسية جاهزة:
    // .findAll() -> بتجيب كل التقييمات
    // .save() -> بتحفظ أو تعدل تقييم
    // .deleteById() -> بتمسح تقييم
    // .findById() -> بتجيب تقييم واحد بالـ ID بتاعه
}