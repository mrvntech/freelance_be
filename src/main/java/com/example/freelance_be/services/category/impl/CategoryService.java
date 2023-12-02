package com.example.freelance_be.services.category.impl;

import com.example.freelance_be.dto.request.category.CreateCategoryRequestBody;
import com.example.freelance_be.dto.response.category.CreateCategoryResponseBody;
import com.example.freelance_be.dto.response.category.GetAllCategoryResponseBody;
import com.example.freelance_be.dto.response.category.GetCategoryResponseBody;
import com.example.freelance_be.entities.Category;
import com.example.freelance_be.exception.exception.BadRequestException;
import com.example.freelance_be.repositories.CategoryRepository;
import com.example.freelance_be.services.category.ICategoryService;
import com.example.freelance_be.services.category.domain.SearchProperties;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class CategoryService implements ICategoryService {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    public CategoryService(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CreateCategoryResponseBody createCategory(CreateCategoryRequestBody requestBody) {
        Optional<Category> existedCategory = categoryRepository.findByName(requestBody.getName());
        if(existedCategory.isPresent())throw new BadRequestException("category already existed");
        Category category = modelMapper.map(requestBody, Category.class);
        categoryRepository.save(category);
        return null;
    }
    @Override
    public GetCategoryResponseBody getCategory(Long id) {
        GetCategoryResponseBody responseBody = new GetCategoryResponseBody();
        Optional<Category> category = categoryRepository.findById(id);
        category.ifPresentOrElse((data) -> responseBody.setCategory(modelMapper.map(data, GetCategoryResponseBody.Category.class)), () -> responseBody.setCategory(null));
        return responseBody;
    }
    @Override
    public GetAllCategoryResponseBody getAllCategory(Map<String, String> allParams) {
        SearchProperties searchProperties = new SearchProperties(allParams);
        List<Category> categories = categoryRepository.findAll(searchProperties.getcategorySpecification());
        GetAllCategoryResponseBody responseBody = new GetAllCategoryResponseBody();
        responseBody.setCategories(categories.stream().map(category -> modelMapper.map(category, com.example.freelance_be.services.category.domain.Category.class)).collect(Collectors.toList()));
        return responseBody;
    }
}