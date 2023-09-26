package com.goldFish.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldFish.entity.UserRegistration;
import com.goldFish.payload.AuthRequest;
import com.goldFish.payload.AuthResponse;
import com.goldFish.repository.UserRegistrationRepository;
import com.goldFish.service.AuthService;
import com.goldFish.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        UserRegistration userDetail = userRegistrationRepository
                .findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Credentials Invalid !!"));

        if (authentication.isAuthenticated()) {
            String accessToken = jwtService.generateToken(userDetail);
            String refreshToken = jwtService.generateRefreshToken(userDetail);

            log.info("Authentocation Completed");
            return AuthResponse
                    .builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }

    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7); //you can also provide "Bearer".length()
        userEmail = jwtService.extractUserName(refreshToken);
        if (userEmail != null) {
            var user = userRegistrationRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Invalid Credential!!"));

            if (jwtService.validateToken(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                var authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
        log.info("Refresh Authentication token Completed");
    }
}
