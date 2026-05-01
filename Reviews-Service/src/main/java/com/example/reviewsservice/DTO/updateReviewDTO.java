package com.example.reviewsservice.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record updateReviewDTO(
        @NotBlank(message = "   The comment cannot be empty ")
        @Size(min = 1, max = 500, message = "  The Review content must be between 10 and 500 characters ")
        String comment,
        @Min(1) @Max(5)
        int rating
) {
}
