package com.example.freelance_be.services.category.domain;

import com.example.freelance_be.entities.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.Map;

public class SearchProperties {
    private Long id;
    private String name;

    public SearchProperties(Map<String, String> allParams) {
        if(allParams.get("id") != null){
            id = Long.valueOf(allParams.get("id"));
        }
        if(allParams.get("name") != null) {
           name = allParams.get("name");
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

    public Specification<Category> getcategorySpecification(){
        Specification<Category> searchSpecification = null;
        if(id != null)searchSpecification = where(hasId(id));
        if(name != null && searchSpecification == null)searchSpecification = where(hasName(name));
        else if (name != null) searchSpecification.and(hasName(name));
        return searchSpecification;
    }

    public Specification<Category> hasId(Long id){
        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("id"), id);
            }
        };
    }
    public Specification<Category> hasName(String name){
        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        };
    }
}
