package com.example.freelance_be.services.job.impl.getalljob;

import com.example.freelance_be.utils.Role;

import java.util.Map;

public class SearchProperties {
    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;
    private Role role;
    private String customerId;
    private String freelancerId;
    private String jobLevel;
    private String typeOfEmployee;
    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getTypeOfEmployee() {
        return typeOfEmployee;
    }

    public void setTypeOfEmployee(String typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public SearchProperties(Map<String, String> allParams) {
        if(allParams.get("id") != null){
            id = Long.valueOf(allParams.get("id"));
        }
        if(allParams.get("name") != null){
            name = allParams.get("name");
        }
        if(allParams.get("categoryId") != null){
            categoryId = Long.valueOf(allParams.get("categoryId"));
        }
        if(allParams.get("categoryName") != null){
            categoryName = allParams.get("categoryName");
        }

        if(allParams.get("role") != null){
            Role.getRole(Integer.parseInt(allParams.get("role"))).ifPresent((userRole -> {
                role = userRole;
            }));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
