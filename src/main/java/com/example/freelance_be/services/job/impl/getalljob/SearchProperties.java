package com.example.freelance_be.services.job.impl.getalljob;

import com.example.freelance_be.entities.Category;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import com.example.freelance_be.entities.Job;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.Map;

public class SearchProperties {
    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;

    public SearchProperties(Map<String, String> allParams) {
        System.out.println(allParams);
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

    public Specification<Job> hasId(Long id){
        return new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("id"), id);
            }
        };
    }
    public Specification<Job> hasCategoryName(String categoryName){
        return new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Job, Category> jobCategory = root.join("category");
                return criteriaBuilder.equal(jobCategory.get("name"), categoryName);
            }
        };
    }

    public Specification<Job> getJobSpecification(){
        Specification<Job> searchSpecification = null;
        if(id != null)searchSpecification = where(hasId(id));
        if(categoryName != null && searchSpecification == null)searchSpecification = where(hasCategoryName(categoryName));
        else if (categoryName != null) searchSpecification.and(hasCategoryName(categoryName));
        return searchSpecification;
    }
}
