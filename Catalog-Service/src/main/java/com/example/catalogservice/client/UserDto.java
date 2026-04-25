package com.example.catalogservice.client;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
}