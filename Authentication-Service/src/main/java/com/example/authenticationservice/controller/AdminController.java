package com.example.authenticationservice.controller;

import com.example.authenticationservice.DTO.AuthResponse;
import com.example.authenticationservice.DTO.UserResponse;
import com.example.authenticationservice.entity.Role;
import com.example.authenticationservice.repository.UserRepository;
import com.example.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String adminTest() {
        return "Hello Admin";
    }
    @PutMapping("/user/{id}/role")
    public ResponseEntity<UserResponse> updateUserRole(@PathVariable Long id,
                                                      @RequestParam Role newRole) {
        return new ResponseEntity<>(userService.updateUserRole(id, newRole), HttpStatus.OK);
    }
    @PutMapping("/user/{id}/toggle")
    public ResponseEntity<UserResponse> toggleUserEnabled(@PathVariable Long id) {
        return new ResponseEntity<>(userService.toggleUserEnabled(id), HttpStatus.OK);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}