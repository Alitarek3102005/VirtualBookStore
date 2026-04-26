package com.example.reviewsservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.reviewsservice.response.BookResponse;

import jakarta.validation.constraints.NotNull;
@FeignClient(name = "catalog-service")
public interface CatalogClient {
    
    @GetMapping("/api/book/{id}") // تأكد إن السطر ده موجود فوق الميثود
    BookResponse getBookById(@PathVariable("id") Long id);

 
}