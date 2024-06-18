package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.HolidayDto;
import com.windmill.rentalservice.model.Holiday;
import com.windmill.rentalservice.service.HolidayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HolidayController.class)
class HolidayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HolidayService holidayService;

    @BeforeEach
    void setUp() {
        HolidayDto holidayDto = new HolidayDto(1L, "Christmas", 2024, 12, 25,
                3, 4, 1, Holiday.HolidayType.ANNUAL);
        Mockito.when(holidayService.getHolidayById(1L)).thenReturn(holidayDto);
        Mockito.when(holidayService.getAllHolidays()).thenReturn(Collections.singletonList(holidayDto));
    }

    @Test
    void testGetHolidayById() throws Exception {
        mockMvc.perform(get("/api/v1/holidays/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("HolidayName")));
    }

    @Test
    void testGetAllHolidays() throws Exception {
        mockMvc.perform(get("/api/v1/holidays")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("HolidayName")));
    }
}
