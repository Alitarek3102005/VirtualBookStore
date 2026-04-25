package com.example.cartservice.client;

import com.example.cartservice.DTO.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "catalog-service")
public interface CatalogClient {

    @GetMapping("/api/book/{id}")
    BookResponse getBookById(@PathVariable Long id);
}