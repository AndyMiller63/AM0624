package com.windmill.rentalservice.service;

import com.windmill.rentalservice.config.auth.TokenProvider;
import com.windmill.rentalservice.controller.AuthController;
import com.windmill.rentalservice.dto.JwtDto;
import com.windmill.rentalservice.dto.SignInDto;
import com.windmill.rentalservice.dto.SignUpDto;
import com.windmill.rentalservice.enums.UserRole;
import com.windmill.rentalservice.model.User;
import com.windmill.rentalservice.repository.UserRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenService;
    @Autowired
    private UserRepository userRepository;


    AuthService(AuthenticationManager authenticationManager, TokenProvider tokenService,
                UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

/**
 * Authenticates a user and generates a JWT token.
 *
 * @param data SignInDto containing user login data
 * @return JwtDto containing the JWT token
 */

    public JwtDto signIn(SignInDto data) {
        logger.debug("Attempting to authenticate user: " + data.getUsername());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var authUser = authenticationManager.authenticate(usernamePassword);
        logger.info("User authenticated successfully: " + authUser.getName()); // Add logging
        var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
        return new JwtDto(accessToken);
    }

    /**
     * Registers a new user.
     *
     * @param data SignUpDto containing user registration data
     * @return UserDetails of the newly registered user
     */
    public UserDetails signUp(SignUpDto data) {
        if (userRepository.findByUsername(data.getUsername()) != null) {
            throw new RuntimeException(AppConstants.USERNAME_EXISTS_ERROR);
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getUsername(), encryptedPassword, UserRole.valueOf(data.getRole()));
        return userRepository.save(newUser);
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.debug(AppConstants.LOADING_USER_BY_USERNAME + username);
        return userRepository.findByUsername(username);
    }
}
