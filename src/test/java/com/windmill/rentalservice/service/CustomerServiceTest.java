package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.CreateCustomerDto;
import com.windmill.rentalservice.dto.CustomerDto;
import com.windmill.rentalservice.mapper.CustomerMapper;
import com.windmill.rentalservice.model.Customer;
import com.windmill.rentalservice.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    AutoCloseable closeable;
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void testCreateCustomer() {
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("customerName", "contactInfo");
        Customer customer = new Customer();
        customer.setName("customerName");
        CustomerDto customerDto = new CustomerDto(1L, "customerName", "contactInfo");

        when(customerMapper.toEntity(createCustomerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.createCustomer(createCustomerDto);

        assertNotNull(result);
        assertEquals("customerName", result.getName());
    }

    @Test
    void testUpdateCustomer() {
        Long id = 1L;
        CustomerDto customerDto = new CustomerDto(id, "updatedCustomerName", "contactInfo");
        Customer customer = new Customer();
        customer.setName("customerName");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.updateCustomer(id, customerDto);

        assertNotNull(result);
        assertEquals("updatedCustomerName", result.getName());
    }

    @Test
    void testDeleteCustomer() {
        Long id = 1L;

        when(customerRepository.existsById(id)).thenReturn(true);

        customerService.deleteCustomer(id);

        verify(customerRepository, times(1)).deleteById(id);
    }
}
