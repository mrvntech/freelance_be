package com.example.freelance_be.dto.response.job;

import java.util.List;

public class GetJobResponseBody {
    private Long id;
    private String name;
    private Double budget;
    private String information;
    private Category category;
    private User customer;
    private User freelancer;
    private String typeOfEmployee;
    private String imageUrl;
    private String jobLevel;
    private List<User> appliers;

    public User getFreelancer() {
        return freelancer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFreelancer(User freelancer) {
        this.freelancer = freelancer;
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

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

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
    public static class Job {
        private Long id;
        private String name;
        private Double budget;
        private String information;
        private Category category;
        private User customer;
        private User freelancer;

        public User getFreelancer() {
            return freelancer;
        }

        public void setFreelancer(User freelancer) {
            this.freelancer = freelancer;
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

        public User getCustomer() {
            return customer;
        }

        public void setCustomer(User customer) {
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
    public static class User {
        private String username;
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
