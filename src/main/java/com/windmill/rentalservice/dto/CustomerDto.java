package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.springframework.data.jpa.domain.AbstractAuditable}
 */
@Value
public class CustomerDto implements Serializable {
    @Schema(description = "Customer ID", example = "1")
    Long customerId;

    @Schema(description = "Customer Name", example = "John Doe")
    String name;

    @Schema(description = "Customer Contact Information", example = "john.doe@example.com")
    String contact;

    public Long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}
