package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.user.UpdateUserProfileRequestBody;
import com.example.freelance_be.dto.response.user.GetUserProfileResponseBody;
import com.example.freelance_be.dto.response.user.UpdateUserProfileResponseBody;
import com.example.freelance_be.services.user.impl.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<GetUserProfileResponseBody> getUserProfile(){
        return ResponseEntity.ok().body(userService.getUserProfile());
    }

    @PutMapping("")
    public ResponseEntity<UpdateUserProfileResponseBody> updateUserProfile(@RequestBody UpdateUserProfileRequestBody requestBody){
        return ResponseEntity.ok().body(userService.updateUserProfile(requestBody));
    }
}
