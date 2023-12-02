package com.example.freelance_be.services.category;

import com.example.freelance_be.dto.request.category.CreateCategoryRequestBody;
import com.example.freelance_be.dto.response.category.CreateCategoryResponseBody;
import com.example.freelance_be.dto.response.category.GetAllCategoryResponseBody;
import com.example.freelance_be.dto.response.category.GetCategoryResponseBody;
import com.example.freelance_be.entities.Category;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface ICategoryService {
    CreateCategoryResponseBody createCategory(CreateCategoryRequestBody requestBody);
    GetCategoryResponseBody getCategory(Long id);
    GetAllCategoryResponseBody getAllCategory(Map<String, String> allParams);
}
