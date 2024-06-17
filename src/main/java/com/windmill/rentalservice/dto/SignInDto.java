package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

@Value
public class SignInDto implements Serializable {
    @Schema(description = "Username", example = "admin")
    String username;

    @Schema(description = "Password", example = "password")
    String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
