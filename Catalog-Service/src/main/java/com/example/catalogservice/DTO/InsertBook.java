package com.example.catalogservice.DTO;

import com.example.catalogservice.entity.Category;
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
