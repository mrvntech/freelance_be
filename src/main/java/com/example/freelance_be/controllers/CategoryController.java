package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.category.CreateCategoryRequestBody;
import com.example.freelance_be.dto.response.category.CreateCategoryResponseBody;
import com.example.freelance_be.dto.response.category.GetAllCategoryResponseBody;
import com.example.freelance_be.dto.response.category.GetCategoryResponseBody;
import com.example.freelance_be.services.category.ICategoryService;
import com.example.freelance_be.services.category.domain.Category;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/categories")
@RestController
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<CreateCategoryResponseBody> createCategory(@RequestBody CreateCategoryRequestBody requestBody){
        return ResponseEntity.ok().body(categoryService.createCategory(requestBody));
    }

    @GetMapping("")
    public ResponseEntity<GetAllCategoryResponseBody> getAllCategory(@RequestParam Map<String, String> allParams){
        return ResponseEntity.ok().body(categoryService.getAllCategory(allParams));
    }

//    @GetMapping("")
//    public ResponseEntity<GetCategoryResponseBody> getCategory(@RequestParam Map<String, String> allParams){
//        return ResponseEntity.ok().body(categoryService.getCategory(allParams));
//    }
}
