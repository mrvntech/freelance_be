package com.example.freelance_be.dto.response.job;

import java.util.List;

public class GetAllJobResponseBody {
    public List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    public static class Job {
        private Long id;
        private String name;
        private Double budget;
        private String information;
        private Category category;
        private Customer customer;
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Category getCategory() {
            return category;
        }
        public void setCategory(Category category) {
            this.category = category;
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

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public static class Category {
            private Long id;
            private String name;

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
        }
    }
    public static class Customer {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
