package com.example.authenticationservice.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String role;


}
