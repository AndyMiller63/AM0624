package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for creating a new brand
 */
@Value
public class CreateBrandDto implements Serializable {
    @Schema(description = "Brand Name", example = "Stihl")
    String brandName;

    public String getBrandName() {
        return brandName;
    }
}
