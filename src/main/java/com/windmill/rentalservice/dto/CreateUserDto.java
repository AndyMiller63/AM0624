package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for creating a new user
 */
@Value
public class CreateUserDto implements Serializable {
    @Schema(description = "Username", example = "admin")
    String username;

    @Schema(description = "Password", example = "password")
    String password;

    @Schema(description = "User Role", example = "ADMIN")
    String role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
