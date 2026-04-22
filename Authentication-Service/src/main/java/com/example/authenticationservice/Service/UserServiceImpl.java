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
        String token = jwt.generateToken(savedUser.getUsername(), String.valueOf(savedUser.getRole()));

        return AuthResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .fullName(savedUser.getFullName())
                .role(savedUser.getRole().name())
                .token(token)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow();

            String token = jwt.generateToken(
                    user.getUsername(),
                    user.getRole().name()
            );

            return AuthResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .fullName(user.getFullName())
                    .role(user.getRole().name())

                    .token(token)
                    .build();
        }

        throw new RuntimeException("Invalid username or password");
    }}