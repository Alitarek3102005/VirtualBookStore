package com.example.authenticationservice.Service;

import com.example.authenticationservice.Dto.AuthResponse;
import com.example.authenticationservice.Dto.LoginRequest;
import com.example.authenticationservice.Dto.RegisterRequest;
import com.example.authenticationservice.Dto.UserMapper;
import com.example.authenticationservice.Entity.User;
import com.example.authenticationservice.Repository.UserRepository;
import com.example.authenticationservice.Security_Config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwt;

    @Override
    public AuthResponse register(RegisterRequest request) {

        // check username
     if (userRepository.findByUsername(request.getUsername()).isPresent()){
         throw new  RuntimeException("Username already exists");
     }
        // check email
    if(userRepository.findByEmail(request.getEmail()).isPresent()){
        throw new RuntimeException("Email already exists");
    }

        // map DTO -> Entity
    User user =userMapper.mapToUser(request);
        // hash password
    user.setPassword(passwordEncoder.encode(user.getPassword()));
        // default values
        user.setEnabled(true);

        // save
    User savedUser=userRepository.save(user);
        // return response
    return userMapper.mapToResponse(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        // 1. authenticate
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwt.generateToken(
                    user.getUsername()
            );

            AuthResponse response = userMapper.mapToResponse(user);

            response.setToken(token);

            return response;
        }

        throw new RuntimeException("Invalid username or password");
    }}