package com.platform.auth.dto;

import java.util.UUID;

public class SignupResponse {

    private UUID userId;
    private String fullName;
    private String token;

    public SignupResponse(UUID userId, String fullName, String token) {
        this.userId = userId;
        this.fullName = fullName;
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getToken() {
        return token;
    }
}

