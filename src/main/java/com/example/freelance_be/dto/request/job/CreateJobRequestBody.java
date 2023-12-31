package com.example.freelance_be.dto.request.job;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateJobRequestBody {
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "budget is required")
    private Double budget;
    @NotNull(message = "information is required")
    private String information;
    @NotNull(message = "categoryId is required")
    private Long categoryId;
    @NotNull(message = "type of employee is required")
    private String typeOfEmployee;
    @NotNull(message = "job level is required")
    private String jobLevel;

    public String getTypeOfEmployee() {
        return typeOfEmployee;
    }

    public void setTypeOfEmployee(String typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
