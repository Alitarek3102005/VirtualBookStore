package com.example.authenticationservice.controller;

import com.example.authenticationservice.DTO.AuthResponse;
import com.example.authenticationservice.DTO.LoginRequest;
import com.example.authenticationservice.DTO.RegisterRequest;
import com.example.authenticationservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }
}