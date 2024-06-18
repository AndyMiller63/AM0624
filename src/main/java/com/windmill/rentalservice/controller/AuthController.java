
package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.JwtDto;
import com.windmill.rentalservice.dto.SignInDto;
import com.windmill.rentalservice.dto.SignUpDto;
import com.windmill.rentalservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService loginService;

    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Register a new user")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto data) {

        loginService.signUp(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return a JWT token")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data) {

        JwtDto jwtDto = loginService.signIn(data);
        return ResponseEntity.ok(jwtDto);
    }
}



