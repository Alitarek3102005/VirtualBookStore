package com.example.authenticationservice.Dto;

import com.example.authenticationservice.Entity.Role;
import com.example.authenticationservice.Entity.User;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {
    public User mapToUser(RegisterRequest request){
        return User.builder().username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .fullName(request.getFullName())
                .address(request.getAddress())

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
