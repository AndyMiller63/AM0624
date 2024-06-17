package com.windmill.rentalservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.windmill.rentalservice.model.ToolQuantity}
 */
@Value
public class ToolQuantityDto implements Serializable {
    Long toolQuantityId;
    Long toolId;
    int inStockCount;
}
