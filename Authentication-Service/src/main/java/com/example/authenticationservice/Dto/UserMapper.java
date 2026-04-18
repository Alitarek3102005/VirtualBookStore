package com.example.authenticationservice.Dto;

import com.example.authenticationservice.Entity.Role;
import com.example.authenticationservice.Entity.User;

public class UserMapper {
    public User mapToUser(RegisterRequest request){
        return User.builder().username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .address(request.getAddress())
                .role(Role.ROLE_USER)
                .build();

    }
    public AuthResponse mapToResponse(User user) {
        return AuthResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .build();
    }
}
