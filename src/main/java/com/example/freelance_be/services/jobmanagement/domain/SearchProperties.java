package com.example.freelance_be.services.jobmanagement.domain;

import com.example.freelance_be.entities.Category;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import com.example.freelance_be.entities.Job;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.Map;
import java.util.Optional;

public class SearchProperties {
    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;
    private Optional<Specification<Job>> jobSpecification = Optional.empty();

    public SearchProperties(Map<String, String> allParams) {
        System.out.println(allParams);
        if(allParams.get("id") != null){
            hasId(Long.valueOf(allParams.get("id")));
        }
        if(allParams.get("name") != null){
            name = allParams.get("name");
        }
        if(allParams.get("categoryId") != null){
            categoryId = Long.valueOf(allParams.get("categoryId"));
        }
        if(allParams.get("categoryName") != null){
            hasCategoryName(allParams.get("categoryName"));
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

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void hasId(Long id){
        if(id == null) return;
        Specification<Job> hasIdSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
        jobSpecification.ifPresentOrElse((specification) -> {
            specification.and(hasIdSpecification);
        }, () -> {
            jobSpecification = Optional.of(where(hasIdSpecification));
        });
    };
    public void hasCategoryName(String categoryName){
        if(categoryName == null) return;
        Specification<Job> hasCategoryNameSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("category").get("name"), categoryName);
        jobSpecification.ifPresentOrElse((specification) -> {
            specification.and(hasCategoryNameSpecification);
        }, () -> {
            jobSpecification = Optional.of(where(hasCategoryNameSpecification));
        });
    }


    public Optional<Specification<Job>> getJobSpecification(){
        return jobSpecification;
    }
}
