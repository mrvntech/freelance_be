package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.profile.CreateProfileRequestBody;
import com.example.freelance_be.dto.request.profile.UpdateProfileRequestBody;
import com.example.freelance_be.dto.response.profile.GetAllProfileResponseBody;
import com.example.freelance_be.dto.response.profile.GetProfileDetailResponseBody;
import com.example.freelance_be.services.profile.impl.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/profile")
@RestController
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("")
    public boolean createProfile(@RequestBody CreateProfileRequestBody body){
        return profileService.createProfile(body);
    }

    @GetMapping("")
    public ResponseEntity<GetAllProfileResponseBody> getProfile(@RequestParam Map<String, String> query){
        return ResponseEntity.ok().body(profileService.getAllProfile(query));
    }

    @PutMapping("")
    public boolean updateProfile(@RequestBody UpdateProfileRequestBody body){
        return profileService.updateProfile(body);
    }

    @DeleteMapping("")
    public boolean deleteProfile(){
        return true;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProfileDetailResponseBody> getProfileDetail(@PathVariable Long id){
        return ResponseEntity.ok().body(profileService.getProfileDetail(id));
    }
}
