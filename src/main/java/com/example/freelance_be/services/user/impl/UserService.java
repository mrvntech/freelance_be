package com.example.freelance_be.services.user.impl;

import com.example.freelance_be.dto.request.user.UpdateUserInformationRequestBody;
import com.example.freelance_be.dto.response.user.GetAllUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.GetUserInformationResponseBody;
import com.example.freelance_be.dto.response.user.UpdateUserInformationResponseBody;
import com.example.freelance_be.entities.Role;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.exception.exception.AuthenticationException;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.minio.iml.MinioService;
import com.example.freelance_be.services.user.IUserService;
import io.minio.errors.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final MinioService minioService;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, MinioService minioService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.minioService = minioService;
    }

    @Override
    public void initAdminAccount() {
        User admin = new User();
        Role adminRole = new Role();
        adminRole.setId(com.example.freelance_be.utils.Role.ADMIN.getValue());
        adminRole.setName(com.example.freelance_be.utils.Role.ADMIN.getName());
        admin.setRoles(Set.of(adminRole));
        userRepository.save(admin);
    }

    @Override
    public GetUserInformationResponseBody getUserInformation() {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        Optional<User> user = userRepository.findByEmail(username);
        if(user.isEmpty())throw new BadRequestException("user is not existed");
        return modelMapper.map(user.get(), GetUserInformationResponseBody.class);
    }

    public GetUserInformationResponseBody getUserInformationById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())throw new BadRequestException("user is not existed");
        return modelMapper.map(user.get(), GetUserInformationResponseBody.class);
    }

    @Override
    public UpdateUserInformationResponseBody updateUserInformation(UpdateUserInformationRequestBody requestBody, MultipartFile file) throws ParseException, ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        System.out.println(requestBody.getDateOfBirth());
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof Jwt)){
            throw new AuthenticationException("Authentication Error");
        }
        String username = (String) ((Jwt) principal).getClaims().get("username");
        User user = userRepository.findByEmail(username).orElseThrow(() -> new BadRequestException("user do not exit"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(requestBody.getDateOfBirth() != null) user.setDateOfBirth(dateFormat.parse(requestBody.getDateOfBirth()));
        if(requestBody.getAddress() != null) user.setAddress(requestBody.getAddress());
        if(requestBody.getFullName() != null) user.setFullName(requestBody.getFullName());
        if(requestBody.getGender() != null) user.setGender(requestBody.getGender());
        if(requestBody.getPhoneNumber() != null) user.setPhoneNumber(requestBody.getPhoneNumber());
        if(requestBody.getPassword() != null) user.setPassword(passwordEncoder.encode(requestBody.getPassword()));
        String imageUrl = minioService.uploadImage(file);
        user.setImageUrl(imageUrl);
        userRepository.save(user);
        return modelMapper.map(user, UpdateUserInformationResponseBody.class);
    }

    @Override
    public GetAllUserInformationResponseBody getAllUser() {
        List<User> users = userRepository.findAll();
        GetAllUserInformationResponseBody responseBody = new GetAllUserInformationResponseBody();
        responseBody.setUserInformationList(users.stream().map(user -> modelMapper.map(user, GetAllUserInformationResponseBody.UserInformation.class)).toList());
        return responseBody;
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("user do not exist"));
        user.setActive(false);
        userRepository.save(user);
        return true;
    }
}
