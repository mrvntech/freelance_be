package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.user.UpdateUserInformationRequestBody;
import com.example.freelance_be.dto.response.user.GetAllUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.GetUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.UpdateUserInformationResponseBody;
import com.example.freelance_be.services.user.impl.UserService;
import io.minio.errors.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/information/detail")
    public ResponseEntity<GetUserInformationResponseBody> getUserProfile(){
        return ResponseEntity.ok().body(userService.getUserInformation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserInformationResponseBody> getUerInformation(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserInformationById(id));
    }

    @GetMapping("")
    public ResponseEntity<GetAllUserInformationResponseBody> getAllUser(){
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @PutMapping("")
    public ResponseEntity<UpdateUserInformationResponseBody> updateUserInformation(@RequestBody UpdateUserInformationRequestBody requestBody) throws ParseException, ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok().body(userService.updateUserInformation(requestBody));
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
