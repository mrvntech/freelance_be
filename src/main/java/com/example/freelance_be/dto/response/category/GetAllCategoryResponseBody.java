package com.example.freelance_be.dto.response.category;


import com.example.freelance_be.services.category.domain.Category;

import java.util.List;

public class GetAllCategoryResponseBody {
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
