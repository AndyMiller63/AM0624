package com.windmill.rentalservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.windmill.rentalservice.dto.CreateRentalDto;
import com.windmill.rentalservice.dto.RentalDto;
import com.windmill.rentalservice.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalController.class)
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService rentalService;

    @Autowired
    private ObjectMapper objectMapper;

    private RentalDto rentalDto;
    private CreateRentalDto createRentalDto;

    @BeforeEach
    void setUp() {
        rentalDto = new RentalDto(1L, 1L, 1L, 7, 10,
                "01/01/24", "2025-01/08/24", 5,
                BigDecimal.valueOf(100.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(90.0));
        createRentalDto = new CreateRentalDto(1L, 1L, 7, 10, "01/01/25");
    }

    @Test
    void testCreateRental() throws Exception {
        Mockito.when(rentalService.createRental(any(CreateRentalDto.class))).thenReturn(rentalDto);

        mockMvc.perform(post("/api/v1/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRentalDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toolId").value(rentalDto.getToolId()));
    }

    @Test
    void testGetRentalById() throws Exception {
        Mockito.when(rentalService.getRentalById(anyLong())).thenReturn(rentalDto);

        mockMvc.perform(get("/api/v1/rentals/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toolId").value(rentalDto.getToolId()));
    }

    @Test
    void testUpdateRental() throws Exception {
        Mockito.when(rentalService.updateRental(anyLong(), any(RentalDto.class))).thenReturn(rentalDto);

        mockMvc.perform(put("/api/v1/rentals/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rentalDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toolId").value(rentalDto.getToolId()));
    }

    @Test
    void testDeleteRental() throws Exception {
        Mockito.doNothing().when(rentalService).deleteRental(anyLong());

        mockMvc.perform(delete("/api/v1/rentals/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAllRentals() throws Exception {
        Mockito.when(rentalService.getAllRentals()).thenReturn(Collections.singletonList(rentalDto));

        mockMvc.perform(get("/api/v1/rentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].toolId").value(rentalDto.getToolId()));
    }
}
