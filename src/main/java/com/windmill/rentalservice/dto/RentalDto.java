package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto implements Serializable {
    @Schema(description = "Rental ID", example = "1")
    Long rentalId;

    @Schema(description = "Tool ID", example = "1")
    Long toolId;

    @Schema(description = "Customer ID", example = "1")
    Long customerId;

    @Schema(description = "Number of rental days", example = "5")
    int rentalDays;

    @Schema(description = "Discount percent", example = "10")
    int discountPercent;

    @Schema(description = "Checkout date", example = "2023-06-20")
    LocalDate checkoutDate;

    @Schema(description = "Due date", example = "2023-06-25")
    LocalDate dueDate;

    @Schema(description = "Number of chargeable days", example = "4")
    int chargeDays;

    @Schema(description = "Pre-discount charge", example = "7.96")
    BigDecimal preDiscountCharge;

    @Schema(description = "Discount amount", example = "0.80")
    BigDecimal discountAmount;

    @Schema(description = "Final charge", example = "7.16")
    BigDecimal finalCharge;

}
