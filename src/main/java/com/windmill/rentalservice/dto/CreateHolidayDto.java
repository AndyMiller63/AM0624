package com.windmill.rentalservice.dto;

import com.windmill.rentalservice.model.Holiday;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

@Value
public class CreateHolidayDto implements Serializable {

    @Schema(description = "Holiday Name", example = "Independence Day")
    String name;

    @Schema(description = "Year of the holiday", example = "2024")
    Integer year;

    @Schema(description = "Month of the holiday", example = "7")
    Integer month;

    @Schema(description = "Day of the month of the holiday", example = "4")
    Integer dayOfMonth;

    @Schema(description = "Day of the week of the holiday", example = "1")
    Integer dayOfWeek;

    @Schema(description = "Week of the month of the holiday", example = "1")
    Integer weekOfMonth;

    @Schema(description = "Duration of the holiday in days", example = "1")
    Integer durationDays;

    @Schema(description = "Type of the holiday", example = "ABSOLUTE|ANNUAL|RELATIVE")
    Holiday.HolidayType holidayType;

}
