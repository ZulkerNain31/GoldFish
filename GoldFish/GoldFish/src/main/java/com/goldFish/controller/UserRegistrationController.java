package com.goldFish.controller;


import com.goldFish.payload.UserRegistrationDTO;
import com.goldFish.service.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping(value = "/signUp")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserRegistrationDTO registrationDTO){
        try{
            log.info("Initializing request to Save the User Data for user having email: {}", registrationDTO.getEmail());
            userRegistrationService.saveUser(registrationDTO);
            return new ResponseEntity<>("User Registration Complete", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Invalid registration");
        }
        return new ResponseEntity<>("Registration Faild!!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/home")
    public ResponseEntity<String> welcomeHome(){
        return new ResponseEntity<>("Welcome to GoldFish", HttpStatus.OK);
    }



}
