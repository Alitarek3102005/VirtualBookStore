package com.example.catalogservice.DTO;

import jakarta.validation.constraints.NotNull;

public record InsertUpdateCategory(
        @NotNull

         String name
) {
}
