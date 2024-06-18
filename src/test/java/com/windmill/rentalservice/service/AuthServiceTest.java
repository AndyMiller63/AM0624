package com.windmill.rentalservice.service;

import com.windmill.rentalservice.config.auth.TokenProvider;
import com.windmill.rentalservice.dto.JwtDto;
import com.windmill.rentalservice.dto.SignInDto;
import com.windmill.rentalservice.enums.UserRole;
import com.windmill.rentalservice.model.User;
import com.windmill.rentalservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    AutoCloseable closeable;
    @Mock
    private UserRepository repository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenProvider tokenService;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    /*
    @Test
    void signUp_UserAlreadyExists_ShouldThrowRuntimeException() {
        SignUpDto signUpDto = new SignUpDto("existingUser", "password", "USER");

        when(repository.findByUsername(signUpDto.getUsername())).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> authService.signUp(signUpDto));
    }

     */

    @Test
    void signIn_ValidCredentials_ShouldReturnJwtDto() {
        SignInDto signInDto = new SignInDto("validUser", "password");
        User user = new User(signInDto.getUsername(), "encodedPassword", UserRole.USER);
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(tokenService.generateAccessToken(any(User.class))).thenReturn("mockedToken");

        JwtDto result = authService.signIn(signInDto);

        assertNotNull(result);
        assertEquals("mockedToken", result.getAccessToken());
    }
}
