package com.example.reviewsservice.client; // غير المسار ده حسب مشروعك

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.reviewsservice.response.BookResponse; // الـ DTO اللي عندك في الصورة

@FeignClient(name = "catalog-service") 
public interface CatalogClient {

    @GetMapping("/api/book/{id}") // تأكد إن الـ Path ده مطابق للي في الـ Catalog Service
    BookResponse getBookById(@PathVariable("id") Long id);
}