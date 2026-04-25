package com.example.authenticationservice.controller;

import com.example.authenticationservice.DTO.UserResponse;
import com.example.authenticationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
    @GetMapping("/test")
    public String publisherTest() {
        return "Hello Publisher";
    }

}