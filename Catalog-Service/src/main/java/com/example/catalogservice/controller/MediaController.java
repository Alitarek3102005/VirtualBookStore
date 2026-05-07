package com.example.catalogservice.controller;

import com.example.catalogservice.service.S3PresignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private S3PresignerService s3Service;
    @GetMapping("/upload-url")
    public ResponseEntity<String> getUploadUrl(@RequestParam String fileName, @RequestParam String contentType) {
        String url = s3Service.generatePresignedUrl(fileName, contentType);
        return ResponseEntity.ok(url);
    }
}