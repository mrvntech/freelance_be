package com.example.freelance_be.services.job.impl;

import java.util.Map;

public class SearchProperties {
    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;

    public SearchProperties(Map<String, String> allParams) {
        id = Long.valueOf(allParams.get("id"));
        name = allParams.get("name");
        categoryId = Long.valueOf(allParams.get("categoryId"));
        categoryName = allParams.get("categoryName");
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

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
