package com.example.reviewsservice.model;

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

    @NotBlank(message = " The comment cannot be empty")
    @Column(nullable = false)
    private String comment;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private int rating;

    
    private Long bookId;
    private String userName; 
}