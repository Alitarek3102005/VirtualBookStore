package com.example.authenticationservice.controller;

import com.example.authenticationservice.DTO.UserResponse;
import com.example.authenticationservice.entity.Role;
import com.example.authenticationservice.entity.User;
import com.example.authenticationservice.service.UserService;
import com.example.authenticationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/test")
    public String adminTest() {
        return "Hello Admin";
    }
    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserResponse> updateUserRole(@PathVariable Long id,
                                                      @RequestParam Role newRole) {
        return new ResponseEntity<>(userService.updateUserRole(id, newRole), HttpStatus.OK);
    }
    @PutMapping("/users/{id}/toggle")
    public ResponseEntity<UserResponse> toggleUserEnabled(@PathVariable Long id) {
        return new ResponseEntity<>(userService.toggleUserEnabled(id), HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUseer(), HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK) ;
    }
    @GetMapping("/publishers")
    public ResponseEntity<List<UserResponse>> getPublisherUsers(){
        return new ResponseEntity<>(userService.getPublisherUsers(Role.PUBLISHER), HttpStatus.OK);
    }

}