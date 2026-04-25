package com.example.catalogservice.DTO;

import com.example.catalogservice.entity.Category;
import jakarta.validation.constraints.NotNull;

public record UpdateBook(
        @NotNull
        Double price,
        Category category
) {
}
