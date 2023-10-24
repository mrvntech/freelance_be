package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.response.user.GetUserProfileResponseBody;
import com.example.freelance_be.dto.response.user.UpdateUserProfileResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("")
    public ResponseEntity<GetUserProfileResponseBody> getUserProfile(){
        return null;
    }

    @PutMapping("")
    public ResponseEntity<UpdateUserProfileResponseBody> updateUserProfile(){
        return null;
    }
}
