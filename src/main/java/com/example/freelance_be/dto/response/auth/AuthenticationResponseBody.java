package com.example.freelance_be.dto.response.auth;

public class AuthenticationResponseBody {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
