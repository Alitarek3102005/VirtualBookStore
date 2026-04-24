package com.example.catalogservice.DTOs;

import com.example.catalogservice.Entities.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public record UpdateBook(
        @NotNull
         Double price,
         Category category
) {
}
