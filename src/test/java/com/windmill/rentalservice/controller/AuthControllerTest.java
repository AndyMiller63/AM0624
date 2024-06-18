package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.JwtDto;
import com.windmill.rentalservice.dto.SignInDto;
import com.windmill.rentalservice.dto.SignUpDto;
import com.windmill.rentalservice.service.AuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    private AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void signUp_ValidRequest_ShouldReturnOk() throws Exception {
        SignUpDto signUpDto = new SignUpDto("newUser", "password", "USER");

        when(authService.signUp(any(SignUpDto.class))).thenReturn(mock(UserDetails.class));

        mockMvc.perform(post("/api/v1/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"newUser\",\"password\":\"password\",\"role\":\"USER\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void signIn_ValidRequest_ShouldReturnOk() throws Exception {
        SignInDto signInDto = new SignInDto("validUser", "password");
        JwtDto jwtDto = new JwtDto("mockedToken");

        when(authService.signIn(any(SignInDto.class))).thenReturn(jwtDto);

        mockMvc.perform(post("/api/v1/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"validUser\",\"password\":\"password\"}"))
                .andExpect(status().isOk());
    }
}
