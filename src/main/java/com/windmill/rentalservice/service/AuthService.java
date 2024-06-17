package com.windmill.rentalservice.service;

import com.windmill.rentalservice.config.auth.TokenProvider;
import com.windmill.rentalservice.dto.SignUpDto;
import com.windmill.rentalservice.model.User;
import com.windmill.rentalservice.repository.UserRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling authentication and authorization.
 * Implements the UserDetailsService interface to load user-specific data.
 */
@Service
public class AuthService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private UserRepository repository;
    private TokenProvider tokenService;

    /**
     * Constructor for dependency injection.
     *
     * @param repository UserRepository for user data operations
     * @param tokenService TokenProvider for JWT token operations
     */
    @Autowired
    public AuthService(UserRepository repository, TokenProvider tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    /**
     * Loads user by username.
     *
     * @param username the username of the user
     * @return UserDetails of the user
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.debug(AppConstants.LOADING_USER_BY_USERNAME + username);
        return repository.findByUsername(username);
    }

    /**
     * Registers a new user.
     *
     * @param data SignUpDto containing user registration data
     * @return UserDetails of the newly registered user
     */
    public UserDetails signUp(SignUpDto data) {
        if (repository.findByUsername(data.username()) != null) {
            throw new RuntimeException(AppConstants.USERNAME_EXISTS_ERROR);
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.role());
        return repository.save(newUser);
    }





    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param data SignInDto containing user login data
     * @return JwtDto containing the JWT token
     */
    // Circular ref.
    /*
    public JwtDto signIn(SignInDto data) {
        System.out.println("Attempting to authenticate user: " + data.username()); // Add logging
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        System.out.println("User authenticated successfully: " + authUser.getName()); // Add logging
        var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
        return new JwtDto(accessToken);
    }
     */
}
