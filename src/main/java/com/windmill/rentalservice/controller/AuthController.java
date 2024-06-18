
package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.config.auth.TokenProvider;
import com.windmill.rentalservice.dto.JwtDto;
import com.windmill.rentalservice.dto.SignInDto;
import com.windmill.rentalservice.dto.SignUpDto;
import com.windmill.rentalservice.model.User;
import com.windmill.rentalservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Register a new user")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto data) {

        authService.signUp(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private JwtDto login(SignInDto data) {

        logger.debug("Attempting to authenticate user: " + data.getUsername());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var authUser = authenticationManager.authenticate(usernamePassword);
        logger.info("User authenticated successfully: " + authUser.getName());
        var accessToken = tokenProvider.generateAccessToken((User) authUser.getPrincipal());
        return new JwtDto(accessToken);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return a JWT token")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data) {

        JwtDto jwtDto = login(data);
        return ResponseEntity.ok(jwtDto);
    }
}



