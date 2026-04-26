package com.example.authenticationservice.service;

import com.example.authenticationservice.DTO.AuthResponse;
import com.example.authenticationservice.DTO.LoginRequest;
import com.example.authenticationservice.DTO.RegisterRequest;
import com.example.authenticationservice.DTO.UserResponse;
import com.example.authenticationservice.entity.Role;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserResponse updateUserRole(Long id, Role newRole);
    UserResponse toggleUserEnabled(Long id);
    void deleteUserById(Long id);


}
