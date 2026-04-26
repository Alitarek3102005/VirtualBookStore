package com.example.authenticationservice.controller;

import com.example.authenticationservice.DTO.UserResponse;
import com.example.authenticationservice.entity.Role;
import com.example.authenticationservice.service.UserService;
import com.example.authenticationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
    @GetMapping("/publishers")
    public List<UserResponse> getPublisherUsers(){
        return userService.getPublisherUsers(Role.PUBLISHER);
    }

}
