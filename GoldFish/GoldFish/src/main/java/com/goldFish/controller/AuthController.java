package com.goldFish.controller;

import com.goldFish.payload.AuthRequest;
import com.goldFish.payload.AuthResponse;
import com.goldFish.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest authRequest){
        try{
            log.info("Authentication Started for User having email: {}", authRequest.getEmail());
            return ResponseEntity.ok(authService.authenticate(authRequest));
        }catch (Exception e){
            log.error("Authentication Failed");
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            log.info("Refresh Authentication token Started");
            authService.refreshToken(request, response);
        }catch (Exception e){
            log.error("Refresh token Failed!!");
            e.printStackTrace();
        }

    }
}
