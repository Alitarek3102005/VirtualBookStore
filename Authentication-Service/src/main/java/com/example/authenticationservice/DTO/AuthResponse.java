package com.example.authenticationservice.DTO;

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
    private String token;


}
