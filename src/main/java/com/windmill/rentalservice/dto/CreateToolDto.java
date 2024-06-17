package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for creating a new tool
 */
@Value
public class CreateToolDto implements Serializable {
    @Schema(description = "Tool Code", example = "CHNS")
    String toolCode;

    @Schema(description = "Tool Type ID", example = "1")
    Long toolTypeId;

    @Schema(description = "Brand ID", example = "1")
    Long brandId;

    public String getToolCode() {
        return toolCode;
    }

    public Long getToolTypeId() {
        return toolTypeId;
    }

    public Long getBrandId() {
        return brandId;
    }
}
