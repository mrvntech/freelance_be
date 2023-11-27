package com.example.freelance_be.services.user.impl;

import com.example.freelance_be.dto.request.user.UpdateUserProfileRequestBody;
import com.example.freelance_be.dto.response.user.GetUserProfileResponseBody;
import com.example.freelance_be.dto.response.user.UpdateUserProfileResponseBody;
import com.example.freelance_be.entities.Role;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initAdminAccount() {
        User admin = new User();
        Role adminRole = new Role();
        adminRole.setId(com.example.freelance_be.utils.Role.ADMIN.getValue());
        adminRole.setName(com.example.freelance_be.utils.Role.ADMIN.getName());
        admin.setRoles(Set.of(adminRole));
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("Admin@123"));
        userRepository.save(admin);
    }

    @Override
    public GetUserProfileResponseBody getUserProfile() {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty())throw new BadRequestException("user is not existed");
        return modelMapper.map(user.get(), GetUserProfileResponseBody.class);
    }

    @Override
    public UpdateUserProfileResponseBody updateUserProfile(UpdateUserProfileRequestBody requestBody) {
        User user = userRepository.findByUsername(requestBody.getUsername()).orElseThrow(()->new BadRequestException("user do not existed"));
        user.setAge(requestBody.getAge());
        user.setSkill(requestBody.getSkill());
        user.setAddress(requestBody.getAddress());
        user.setLevel(requestBody.getLevel());
        user.setDescription(requestBody.getDescription());
        userRepository.save(user);
        return modelMapper.map(user, UpdateUserProfileResponseBody.class);
    }
}
