package com.example.freelance_be.dto.response.user;

import java.util.Date;
import java.util.List;

public class GetAllUserInformationResponseBody {
    private List<UserInformation> userInformationList;

    public List<UserInformation> getUserInformationList() {
        return userInformationList;
    }

    public void setUserInformationList(List<UserInformation> userInformationList) {
        this.userInformationList = userInformationList;
    }

    public static class UserInformation {
        private Long id;
        private String email;
        private String address;
        private String gender;
        private Date dateOfBirth;
        private String fullName;
        private String phoneNumber;
        private String imageUrl;
        private boolean active;

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
