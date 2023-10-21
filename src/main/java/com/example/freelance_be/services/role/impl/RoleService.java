package com.example.freelance_be.services.role.impl;

import com.example.freelance_be.entities.Role;
import com.example.freelance_be.repositories.RoleRepository;
import com.example.freelance_be.services.role.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initRole() {
        Role admin = new Role();
        admin.setId(com.example.freelance_be.utils.Role.ADMIN.getValue());
        admin.setName(com.example.freelance_be.utils.Role.ADMIN.getName());

        Role freelance = new Role();
        freelance.setId(com.example.freelance_be.utils.Role.FREELANCE.getValue());
        freelance.setName(com.example.freelance_be.utils.Role.FREELANCE.getName());

        Role customer = new Role();
        customer.setId(com.example.freelance_be.utils.Role.CUSTOMER.getValue());
        customer.setName(com.example.freelance_be.utils.Role.CUSTOMER.getName());

        repository.saveAll(List.of(admin, customer, freelance));
    }
}
