package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.BrandDto;
import com.windmill.rentalservice.service.BrandService;
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

@WebMvcTest(BrandController.class)
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        BrandDto brandDto = new BrandDto(1L, "BrandName");
        Mockito.when(brandService.getBrandById(1L)).thenReturn(brandDto);
        Mockito.when(brandService.getAllBrands()).thenReturn(Collections.singletonList(brandDto));
    }

    @Test
    void testGetBrandById() throws Exception {
        mockMvc.perform(get("/api/v1/brands/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.brandName", is("BrandName")));
    }

    @Test
    void testGetAllBrands() throws Exception {
        mockMvc.perform(get("/api/v1/brands")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].brandId", is(1)))
                .andExpect(jsonPath("$[0].brandName", is("BrandName")));
    }
}
