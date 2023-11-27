package com.example.freelance_be.services.profile.impl;

import com.example.freelance_be.dto.request.profile.CreateProfileRequestBody;
import com.example.freelance_be.dto.request.profile.UpdateProfileRequestBody;
import com.example.freelance_be.dto.response.profile.GetAllProfileResponseBody;
import com.example.freelance_be.dto.response.profile.GetProfileDetailResponseBody;
import com.example.freelance_be.entities.Category;
import com.example.freelance_be.entities.Level;
import com.example.freelance_be.entities.Profile;
import com.example.freelance_be.entities.WorkingType;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.CategoryRepository;
import com.example.freelance_be.repositories.LevelRepository;
import com.example.freelance_be.repositories.ProfileRepository;
import com.example.freelance_be.repositories.WorkingTypeRepository;
import com.example.freelance_be.services.profile.IProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService implements IProfileService {
    private final ProfileRepository profileRepository;
    private final CategoryRepository categoryRepository;
    private final WorkingTypeRepository workingTypeRepository;
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    public ProfileService(ProfileRepository profileRepository, CategoryRepository categoryRepository, WorkingTypeRepository workingTypeRepository, LevelRepository levelRepository, ModelMapper modelMapper) {
        this.profileRepository = profileRepository;
        this.categoryRepository = categoryRepository;
        this.workingTypeRepository = workingTypeRepository;
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createProfile(CreateProfileRequestBody body) {
        Category category = categoryRepository.findById(body.getCategoryId()).orElseThrow(() -> new BadRequestException("category do not exist"));
        Level level= levelRepository.findById(body.getLevelId()).orElseThrow(() -> new BadRequestException("level do not exist"));
        WorkingType workingType = workingTypeRepository.findById(body.getWorkingTypeId()).orElseThrow(() -> new BadRequestException("working type do not exist"));
        Profile profile = modelMapper.map(body, Profile.class);
        profile.setCategory(category);
        profile.setLevel(level);
        profile.setWorkingType(workingType);
        profileRepository.save(profile);
        return true;
    }

    @Override
    public boolean updateProfile(UpdateProfileRequestBody body) {
        Profile profile = profileRepository.findById(body.getId()).orElseThrow(() -> new BadRequestException("profile do not exist"));
        Category category = categoryRepository.findById(body.getCategoryId()).orElseThrow(() -> new BadRequestException("category do not exist"));
        Level level= levelRepository.findById(body.getLevelId()).orElseThrow(() -> new BadRequestException("level do not exist"));
        WorkingType workingType = workingTypeRepository.findById(body.getWorkingTypeId()).orElseThrow(() -> new BadRequestException("working type do not exist"));
        profile.setSkill(body.getSkill());
        profile.setWorkExperience(body.getWorkExperience());
        profile.setAboutMe(body.getAboutMe());
        profile.setCategory(category);
        profile.setLevel(level);
        profile.setWorkingType(workingType);
        profileRepository.save(profile);
        return true;
    }

    @Override
    public GetAllProfileResponseBody getAllProfile() {
        List<Profile> profiles = profileRepository.findAll();
        GetAllProfileResponseBody responseBody = new GetAllProfileResponseBody();
        responseBody.setProfiles(profiles.stream().map(profile -> modelMapper.map(profile, GetAllProfileResponseBody.Profile.class)).toList());
        return responseBody;
    }

    @Override
    public GetProfileDetailResponseBody getProfileDetail(Long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        GetProfileDetailResponseBody responseBody = new GetProfileDetailResponseBody();
        profile.ifPresentOrElse((value) -> responseBody.setProfile(modelMapper.map(profile, GetProfileDetailResponseBody.Profile.class)), () -> responseBody.setProfile(null));
        return responseBody;
    }
}
