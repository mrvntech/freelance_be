package com.example.freelance_be.dto.request.category;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryRequestBody {
    @NotBlank(message = "name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
