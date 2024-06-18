package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.CreateCustomerDto;
import com.windmill.rentalservice.dto.CustomerDto;
import com.windmill.rentalservice.mapper.CustomerMapper;
import com.windmill.rentalservice.model.Customer;
import com.windmill.rentalservice.repository.CustomerRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling Customer related operations.
 */
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * Constructor for dependency injection.
     *
     * @param customerRepository CustomerRepository for customer data operations
     * @param customerMapper CustomerMapper for mapping between Customer entity and DTO
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * Creates a new Customer.
     *
     * @param createCustomerDto CreateCustomerDto containing customer creation data
     * @return CustomerDto of the newly created customer
     */
    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = customerMapper.toEntity(createCustomerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    /**
     * Updates an existing Customer.
     *
     * @param id Long id of the customer to update
     * @param customerDto CustomerDto containing updated customer data
     * @return CustomerDto of the updated customer
     */
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setName(customerDto.getName());
            existingCustomer.setContact(customerDto.getContact());
            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return customerMapper.toDto(updatedCustomer);
        } else {
            throw new RuntimeException(AppConstants.CUSTOMER_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Deletes a Customer by id.
     *
     * @param id Long id of the customer to delete
     */
    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new RuntimeException(AppConstants.CUSTOMER_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Retrieves a Customer by id.
     *
     * @param id Long id of the customer to retrieve
     * @return CustomerDto of the retrieved customer
     */
    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(AppConstants.CUSTOMER_NOT_FOUND_ERROR + id));
        return customerMapper.toDto(customer);
    }

    /**
     * Retrieves all Customers.
     *
     * @return List of CustomerDto containing all customers
     */
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }
}
