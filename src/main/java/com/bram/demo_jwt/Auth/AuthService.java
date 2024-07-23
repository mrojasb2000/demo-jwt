package com.bram.demo_jwt.Auth;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bram.demo_jwt.User.Role;
import com.bram.demo_jwt.User.User;
import com.bram.demo_jwt.User.UserRepository;
import com.bram.demo_jwt.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService JwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .country(request.getCountry())
            .role(Role.USER)
            .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .token(JwtService.getToken(user))
                .build();
    }

}
