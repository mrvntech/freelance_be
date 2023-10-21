package com.example.freelance_be.dto.request.auth;

public class AuthenticationRequestBody {
    private String username;
    private String tokenId;
    public String getTokenId() {
        return tokenId;
    }
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
