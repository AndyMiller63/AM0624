package com.windmill.rentalservice.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for JWT response.
 */
public class JwtDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accessToken;

    public JwtDto() {
    }

    public JwtDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
