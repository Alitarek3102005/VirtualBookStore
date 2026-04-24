package com.example.authenticationservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @GetMapping("/test")
    public String publisherTest() {
        return "Hello Publisher";
    }
}