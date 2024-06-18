package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.ToolDto;
import com.windmill.rentalservice.service.ToolService;
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

@WebMvcTest(ToolController.class)
class ToolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolService toolService;

    @BeforeEach
    void setUp() {
        ToolDto toolDto = new ToolDto(1L, "ToolCode", 1L, 1L);
        Mockito.when(toolService.getToolById(1L)).thenReturn(toolDto);
        Mockito.when(toolService.getAllTools()).thenReturn(Collections.singletonList(toolDto));
    }

    @Test
    void testGetToolById() throws Exception {
        mockMvc.perform(get("/api/v1/tools/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toolId", is(1)))
                .andExpect(jsonPath("$.toolCode", is("ToolCode")));
    }

    @Test
    void testGetAllTools() throws Exception {
        mockMvc.perform(get("/api/v1/tools")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].toolId", is(1)))
                .andExpect(jsonPath("$[0].toolCode", is("ToolCode")));
    }
}
