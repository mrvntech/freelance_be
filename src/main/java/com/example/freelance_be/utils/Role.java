package com.example.freelance_be.utils;

import java.util.Optional;

public enum Role {
    ADMIN(1, "ADMIN"),
    FREELANCE(2, "FREELANCE"),
    CUSTOMER(3, "CUSTOMER")
    ;
    private final int value;
    private final String name;

    Role(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Optional<Role> getRole(int value){
        for(Role role: Role.values()){
            if(role.value == value)return Optional.of(role);
        }
        return Optional.empty();
    }
}
