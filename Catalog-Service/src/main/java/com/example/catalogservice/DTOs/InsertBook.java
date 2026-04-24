package com.example.catalogservice.DTOs;

import com.example.catalogservice.Entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public record InsertBook(
        @NotNull
         String title,
         @NotNull
         String author,
         @NotNull
         Double price,
         Category category,
         Long publisher_id,
         @NotNull
         Long quantity
) {

}
