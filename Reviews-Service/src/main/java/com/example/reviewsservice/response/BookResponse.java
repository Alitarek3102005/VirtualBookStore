package com.example.reviewsservice.response;

public record BookResponse(
    Long id,
    String title, 
    String author
) {}