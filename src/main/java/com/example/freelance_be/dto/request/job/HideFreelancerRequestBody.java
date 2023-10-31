package com.example.freelance_be.dto.request.job;

public class HideFreelancerRequestBody {
    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    private Long freelancerId;

}
