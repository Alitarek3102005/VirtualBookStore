package com.example.authenticationservice.controller;

import com.example.authenticationservice.DTO.AuthResponse;
import com.example.authenticationservice.DTO.UserResponse;
import com.example.authenticationservice.entity.Role;
import com.example.authenticationservice.service.UserService;
import com.example.authenticationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/test")
    public String userTest() {
        return "Hello User";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK) ;
    }
    @GetMapping("/publishers")
    public ResponseEntity<List<UserResponse>> getPublisherUsers(){
        return new ResponseEntity<>(userService.getPublisherUsers(Role.PUBLISHER), HttpStatus.OK);
    }
}
