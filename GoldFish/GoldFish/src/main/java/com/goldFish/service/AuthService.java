package com.goldFish.service;

import com.goldFish.payload.AuthRequest;
import com.goldFish.payload.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
