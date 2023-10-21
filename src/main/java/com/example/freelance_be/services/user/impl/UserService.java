package com.example.freelance_be.services.user.impl;

import com.example.freelance_be.entities.Role;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.user.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
