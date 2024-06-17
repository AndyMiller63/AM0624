package com.windmill.rentalservice.dto;

import com.windmill.rentalservice.enums.UserRole;

public record SignUpDto(String username, String password, UserRole role) {}
