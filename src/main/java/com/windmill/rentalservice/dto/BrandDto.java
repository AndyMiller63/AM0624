package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.springframework.data.jpa.domain.AbstractAuditable}
 */
@Value
public class BrandDto implements Serializable {
    @Schema(description = "Brand ID", example = "1")
    Long brandId;

    @Schema(description = "Brand Name", example = "Stihl")
    String brandName;

}
