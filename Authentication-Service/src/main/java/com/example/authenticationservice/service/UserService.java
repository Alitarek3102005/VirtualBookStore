package com.example.authenticationservice.service;

import com.example.authenticationservice.DTO.AuthResponse;
import com.example.authenticationservice.DTO.LoginRequest;
import com.example.authenticationservice.DTO.RegisterRequest;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);


}
