package com.donor.donorapp.auth;

import com.donor.donorapp.dto.RegisterResponseDto;
import org.springframework.stereotype.Service;
import com.donor.donorapp.repositories.UserRepository;
import com.donor.donorapp.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import com.donor.donorapp.dto.UserRequestDto;
import com.donor.donorapp.mapper.UserMapper;
import com.donor.donorapp.models.Role;
import com.donor.donorapp.models.User;
import com.donor.donorapp.dto.LoginRequestDto;
import com.donor.donorapp.dto.AuthResponseDto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponseDto register(UserRequestDto request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = UserMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER);

        userRepository.save(user);

        return new RegisterResponseDto("User registered successfully");
    }

    public AuthResponseDto login(LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("user not found."));

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponseDto(
                token,
                user.getEmail(),
                user.getRole().name()
        );
    }
}
