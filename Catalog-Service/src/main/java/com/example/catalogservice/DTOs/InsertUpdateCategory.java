package com.example.catalogservice.DTOs;

import jakarta.validation.constraints.NotNull;

public record InsertUpdateCategory(
        @NotNull

         String name
) {
}
