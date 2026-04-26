package com.example.orderservice.feign;


import com.example.orderservice.DTO.CartDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("cart-service")
public interface OrderInterface {
    @GetMapping("/{userId}")
    CartDTO getCart(@PathVariable Long userId);
}
