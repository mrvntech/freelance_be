package com.example.freelance_be.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double budget;
    private String information;
    private String imageUrl;
    private Date date;
    private String status;
    private String typeOfEmployee;
    private String jobLevel;

    public String getTypeOfEmployee() {
        return typeOfEmployee;
    }

    public void setTypeOfEmployee(String typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public List<User> getAppliers() {
        return appliers;
    }

    public void setAppliers(List<User> appliers) {
        this.appliers = appliers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;


    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User customer;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "freelancer_id", referencedColumnName = "id")
    private User freelancer;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinTable(name = "apply_job", joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "applierId", referencedColumnName = "id"))
    private List<User> appliers;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(User freelancer) {
        this.freelancer = freelancer;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", information='" + information + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", typeOfEmployee='" + typeOfEmployee + '\'' +
                ", jobLevel='" + jobLevel + '\'' +
                ", category=" + category +
                ", customer=" + customer +
                ", freelancer=" + freelancer +
                '}';
    }
}
