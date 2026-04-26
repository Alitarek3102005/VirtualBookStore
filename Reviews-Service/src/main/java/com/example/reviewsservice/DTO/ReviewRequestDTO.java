package com.example.reviewsservice.DTO;

import jakarta.validation.constraints.*;

public record ReviewRequestDTO(
    @NotBlank(message = "   The comment cannot be empty ")
    @Size(min = 10, max = 500, message = "  The Review content must be between 10 and 500 characters ")
    String comment,
    @NotBlank(message = " The username cannot be empty ")
    String userName,
    @Min(1) @Max(5)
    int rating,

    @NotNull(message = " You must provide a bookId for the review ")
    Long bookId
) {

}