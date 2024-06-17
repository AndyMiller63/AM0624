package com.windmill.rentalservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.windmill.rentalservice.dto.ToolDto;
import com.windmill.rentalservice.exception.RentalCreationException;
import com.windmill.rentalservice.model.Rental;
import com.windmill.rentalservice.util.AppConstants;
import com.windmill.rentalservice.util.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RentalServiceTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ToolService toolService;

    @InjectMocks
    private RentalService rentalService;

    private ToolDto toolDto;
    private Rental rental;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        toolDto = new ToolDto();
        toolDto.setToolId(1L);
        rental = new Rental();
        rental.setRentalId(1L);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testValidateInputsAndReturnTool_Success() throws Exception {
        when(toolService.getToolById(anyLong())).thenReturn(toolDto);

        ToolDto result = rentalService.validateInputsAndReturnTool(1L, 5, 10, "06/11/24");

        assertNotNull(result);
        assertEquals(toolDto.getToolId(), result.getToolId());
        verify(toolService, times(1)).getToolById(1L);
    }

    @Test
    void testValidateInputsAndReturnTool_InvalidRentalDays() {
        Exception exception = assertThrows(RentalCreationException.class, () -> {
            rentalService.validateInputsAndReturnTool(1L, 0, 10, "06/11/24");
        });

        assertEquals(AppConstants.RENTAL_DAY_COUNT_INVALID, exception.getMessage());
    }

    @Test
    void testValidateInputsAndReturnTool_InvalidDiscountPercent() {
        Exception exception = assertThrows(RentalCreationException.class, () -> {
            rentalService.validateInputsAndReturnTool(1L, 5, 110, "06/11/24");
        });

        assertEquals(AppConstants.DISCOUNT_PERCENT_INVALID, exception.getMessage());
    }

    @Test
    void testValidateInputsAndReturnTool_InvalidCheckoutDate() {
        when(Utility.isDateValid(anyString())).thenReturn(false);

        Exception exception = assertThrows(RentalCreationException.class, () -> {
            rentalService.validateInputsAndReturnTool(1L, 5, 10, "2024-11-11");
        });

        assertEquals(AppConstants.DATE_NOT_VALID, exception.getMessage());
    }

    @Test
    void testValidateInputsAndReturnTool_ToolNotRecognized() {
        when(toolService.getToolById(anyLong())).thenReturn(null);

        Exception exception = assertThrows(RentalCreationException.class, () -> {
            rentalService.validateInputsAndReturnTool(1L, 5, 10, "2024-06-11");
        });

        assertEquals(String.format(AppConstants.TOOL_NOT_RECOGNIZED, 1L), exception.getMessage());
    }

    @Test
    void testCreateRental() throws Exception {
      /*
        when(rentalService.createRental(any(CreateRentalDto.class))).thenReturn(rental);

        mockMvc.perform(post("/api/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rental)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rentalId").value(rental.getRentalId()));

       */
    }

    @Test
    void testCreateRentalWithException() throws Exception {
        /*
        when(rentalService.createRental(any(Rental.class))).thenThrow(new RentalCreationException(AppConstants.TOOL_NOT_AVAILABLE));

        mockMvc.perform(post("/api/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rental)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(AppConstants.TOOL_NOT_AVAILABLE));

         */
    }

    @Test
    void testGetRentalById() throws Exception {
        /*
        when(rentalService.findRentalByRentalId(anyLong())).thenReturn(rental);

        mockMvc.perform(get("/api/rentals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rentalId").value(rental.getRentalId()));
            */
    }
}