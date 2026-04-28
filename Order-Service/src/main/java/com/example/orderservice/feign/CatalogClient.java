package com.example.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("catalog-service")
public interface CatalogClient {

    @PutMapping("/api/book/{id}/reduce-stock")
    void reduceStock(@PathVariable Long id, @RequestParam Long quantity);

    @GetMapping("/api/book/{id}/check-stock")
    Boolean checkStock(@PathVariable Long id, @RequestParam Long quantity);
}
