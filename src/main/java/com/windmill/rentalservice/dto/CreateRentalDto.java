package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for creating a new rental
 */
@Value
public class CreateRentalDto implements Serializable {
    @Schema(description = "Tool ID for the rental", example = "1")
    Long toolId;

    @Schema(description = "Customer ID for the rental", example = "1")
    Long customerId;

    @Schema(description = "Number of rental days", example = "5")
    int rentalDays;

    @Schema(description = "Discount percentage", example = "10")
    int discountPercent;

    @Schema(description = "Checkout date in MM/dd/yy format", example = "06/10/24")
    String checkoutDateString;

    public Long getToolId() {
        return toolId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public String getCheckoutDateString() {
        return checkoutDateString;
    }
}
