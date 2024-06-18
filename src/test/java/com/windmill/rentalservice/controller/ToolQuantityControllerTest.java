package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.ToolQuantityDto;
import com.windmill.rentalservice.service.ToolQuantityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ToolQuantityController.class)
class ToolQuantityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolQuantityService toolQuantityService;

    @BeforeEach
    void setUp() {
        ToolQuantityDto toolQuantityDto = new ToolQuantityDto(1L, 1L, 5);
        Mockito.when(toolQuantityService.getToolQuantityByToolId(1L)).thenReturn(toolQuantityDto);
    }

    @Test
    void testGetToolQuantityByToolId() throws Exception {
        mockMvc.perform(get("/api/v1/tool-quantities/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toolId", is(1)))
                .andExpect(jsonPath("$.inStockCount", is(5)));
    }
}
