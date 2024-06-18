package com.windmill.rentalservice.service;

import com.windmill.rentalservice.config.auth.TokenProvider;
import com.windmill.rentalservice.dto.SignUpDto;
import com.windmill.rentalservice.enums.UserRole;
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

@Service
public class AuthService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    //private AuthenticationManager authenticationManager; // <-- caused circular ref.

    private TokenProvider tokenProvider;

    private UserRepository userRepository;

    @Autowired
    AuthService(//AuthenticationManager authenticationManager,
                TokenProvider tokenProvider,
                UserRepository userRepository) {
        //this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }

    /**
     * Login an existing user.
     *
     * @param data SignInDto containing user registration data
     * @return UserDetails of the newly registered user
     */
    /*
    public JwtDto signIn(SignInDto data) {
        System.out.println("Attempting to authenticate user: " + data.getUsername());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var authUser = authenticationManager.authenticate(usernamePassword);
        System.out.println("User authenticated successfully: " + authUser.getName());
        var accessToken = tokenProvider.generateAccessToken((User) authUser.getPrincipal());
        return new JwtDto(accessToken);
    }

     */

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
