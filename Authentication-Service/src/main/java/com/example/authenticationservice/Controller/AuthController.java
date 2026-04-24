package com.example.authenticationservice.Controller;

import com.example.authenticationservice.Dto.AuthResponse;
import com.example.authenticationservice.Dto.LoginRequest;
import com.example.authenticationservice.Dto.RegisterRequest;
import com.example.authenticationservice.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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

    // ================= LOGIN =================
    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }
}