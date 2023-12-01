package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.user.UpdateUserInformationRequestBody;
import com.example.freelance_be.dto.response.user.GetAllUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.GetUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.UpdateUserInformationResponseBody;
import com.example.freelance_be.services.user.impl.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<GetUserInformationResponseBody> getUserProfile(){
        return ResponseEntity.ok().body(userService.getUserInformation());
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllUserInformationResponseBody> getAllUser(){
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @PutMapping(value = "")
    public ResponseEntity<UpdateUserInformationResponseBody> updateUserInformation(@RequestPart("form") UpdateUserInformationRequestBody requestBody) throws ParseException {
        return ResponseEntity.ok().body(userService.updateUserInformation(requestBody));
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
