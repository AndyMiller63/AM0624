package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for creating a new customer
 */
@Value
public class CreateCustomerDto implements Serializable {
    @Schema(description = "Customer Name", example = "John Doe")
    String name;

    @Schema(description = "Customer Contact Information", example = "john.doe@example.com")
    String contact;

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}
