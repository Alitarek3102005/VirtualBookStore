package com.example.orderservice.feign;


import com.example.orderservice.DTO.CartDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("cart-service")
public interface OrderInterface {
    @GetMapping("/api/cart/{userId}")
    CartDTO getCart(@PathVariable Long userId);

    @DeleteMapping("/api/cart/{userId}/clear")
    void clearCart(@PathVariable Long userId);

}
