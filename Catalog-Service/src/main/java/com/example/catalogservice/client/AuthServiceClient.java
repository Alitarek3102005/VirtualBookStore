package com.example.catalogservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "authentication-service")
public interface AuthServiceClient {
    @GetMapping("/api/user/{id}")
    UserDto getPublisherById(@PathVariable("id") Long id);
}