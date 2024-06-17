package com.windmill.rentalservice.mapper;

import com.windmill.rentalservice.dto.CreateCustomerDto;
import com.windmill.rentalservice.dto.CustomerDto;
import com.windmill.rentalservice.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerDto(
                customer.getCustomerId(),
                customer.getName(),
                customer.getContact()
        );
    }

    public Customer toEntity(CustomerDto dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setCustomerId(dto.getCustomerId());
        customer.setName(dto.getName());
        customer.setContact(dto.getContact());
        return customer;
    }

    public Customer toEntity(CreateCustomerDto dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        //customer.setCustomerId(0L);
        customer.setName(dto.getName());
        customer.setContact(dto.getContact());
        return customer;
    }
}
