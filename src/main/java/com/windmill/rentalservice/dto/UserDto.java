package com.windmill.rentalservice.dto;

import com.windmill.rentalservice.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    // Getters and Setters
    private long userId;
    private String username;
    private String password;
    private UserRole role;

    // Default constructor
    public UserDto() {
    }

    // Parameterized constructor
    public UserDto(long userId, String username, String password, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
