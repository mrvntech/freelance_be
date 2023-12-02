package com.example.freelance_be.dto.request.application;

public class UpdateApplicationRequestBody {
    private Long profileId;
    private String status;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
