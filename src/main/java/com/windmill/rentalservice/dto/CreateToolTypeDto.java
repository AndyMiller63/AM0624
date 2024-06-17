package com.windmill.rentalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for creating a new tool type
 */
@Value
public class CreateToolTypeDto implements Serializable {
    @Schema(description = "Tool Type Name", example = "Chainsaw")
    String toolTypeName;

    @Schema(description = "Daily Charge for the tool type", example = "1.99")
    double dailyCharge;

    @Schema(description = "Weekday Charge applicability", example = "true")
    boolean weekdayCharge;

    @Schema(description = "Weekend Charge applicability", example = "true")
    boolean weekendCharge;

    @Schema(description = "Holiday Charge applicability", example = "false")
    boolean holidayCharge;

    public String getToolTypeName() {
        return toolTypeName;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
