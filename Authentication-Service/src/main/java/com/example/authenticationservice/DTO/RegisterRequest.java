package com.example.authenticationservice.DTO;

import com.example.authenticationservice.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$"
    )
    private String password;

    @NotNull
    private Role role;

    @Size(max = 50)
    private String fullName;

    @Size(max = 255)
    private String address;
}