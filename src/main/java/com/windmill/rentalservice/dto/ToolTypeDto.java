package com.windmill.rentalservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ToolTypeDto implements Serializable {
    private Long toolTypeId;
    private String toolTypeName;
    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    public ToolTypeDto(Long toolTypeId, String toolTypeName, double dailyCharge,
                       boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.toolTypeId = toolTypeId;
        this.toolTypeName = toolTypeName;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public ToolTypeDto() {
    }

    public Long getToolTypeId() {
        return toolTypeId;
    }

    public void setToolTypeId(Long toolTypeId) {
        this.toolTypeId = toolTypeId;
    }

    public String getToolTypeName() {
        return toolTypeName;
    }

    public void setToolTypeName(String toolTypeName) {
        this.toolTypeName = toolTypeName;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }
}
