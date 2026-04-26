package com.example.catalogservice.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private String description;
    private String imageUrl;
    private String categoryName;
    private String publisherName;
    private Long quantity;
}