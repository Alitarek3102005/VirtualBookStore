package com.example.authenticationservice.Service;

import com.example.authenticationservice.Dto.AuthResponse;
import com.example.authenticationservice.Dto.LoginRequest;
import com.example.authenticationservice.Dto.RegisterRequest;
import com.example.authenticationservice.Entity.User;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);


}
