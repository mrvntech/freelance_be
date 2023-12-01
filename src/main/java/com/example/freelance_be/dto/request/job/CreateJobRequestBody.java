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
    @NotNull(message = "category is required")
    private Long categoryId;
    @NotNull(message = "working type is required")
    private Long workingTypeId;
    @NotNull(message = "job level is required")
    private Long levelId;
    private String dueDate;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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

    public Long getWorkingTypeId() {
        return workingTypeId;
    }

    public void setWorkingTypeId(Long workingTypeId) {
        this.workingTypeId = workingTypeId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }
}
