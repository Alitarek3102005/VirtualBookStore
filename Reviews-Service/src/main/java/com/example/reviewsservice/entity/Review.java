package com.example.reviewsservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity 
@Table(name = "reviews") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    // حدد اسم العمود بالظبط زي الصورة
    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "book_id") // لازم تطابق الصورة
    private Long bookId;

    @Column(name = "user_name") // لازم تطابق الصورة
    private String userName; 
}