package com.example.authenticationservice.DTO;

import com.example.authenticationservice.entity.Role;
import com.example.authenticationservice.entity.User;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {
    public User mapToUser(RegisterRequest request){
        Role role;

        if (request.getRole() == Role.PUBLISHER) {
            role = Role.PUBLISHER;
        }
        else {
            role = Role.USER;
        }
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
