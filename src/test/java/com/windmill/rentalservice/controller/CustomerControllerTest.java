package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.CustomerDto;
import com.windmill.rentalservice.service.CustomerService;
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

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        CustomerDto customerDto = new CustomerDto(1L, "CustomerName", "CustomerContact");
        Mockito.when(customerService.getCustomerById(1L)).thenReturn(customerDto);
        Mockito.when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customerDto));
    }

    @Test
    void testGetCustomerById() throws Exception {
        mockMvc.perform(get("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.name", is("CustomerName")))
                .andExpect(jsonPath("$.contact", is("CustomerContact")));
    }

    @Test
    void testGetAllCustomers() throws Exception {
        mockMvc.perform(get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerId", is(1)))
                .andExpect(jsonPath("$[0].name", is("CustomerName")))
                .andExpect(jsonPath("$[0].contact", is("CustomerContact")));
    }
}
