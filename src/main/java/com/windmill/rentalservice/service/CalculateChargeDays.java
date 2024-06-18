package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.ToolTypeDto;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Utility class for calculating chargeable days based on tool type and rental period.
 */
public class CalculateChargeDays {

    private final HolidayService holidayService;
    CalculateChargeDays(HolidayService holidayService) {

        this.holidayService = holidayService;
    }

    /**
     * Enum to represent different types of days.
     */
    public enum DayType {WEEKDAY, WEEKEND, HOLIDAY}

    /**
     * Method to calculate the number of chargeable days based on the tool type and rental period.
     *
     * @param toolTypeDto ToolTypeDto containing tool type information
     * @param startDate LocalDate representing the start date of the rental
     * @param numberOfDays int representing the number of rental days
     * @return int representing the number of chargeable days
     */
    public int getChargeDaysCount(ToolTypeDto toolTypeDto, LocalDate startDate, int numberOfDays) {
        int chargeDaysCount = 0;
        for (int i = 0; i < numberOfDays; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            DayType dayType = getDayType(currentDate);
            if (isChargeableDay(toolTypeDto, dayType)) {
                chargeDaysCount++;
            }
        }
        return chargeDaysCount;
    }

    /**
     * Method to determine the type of a given day.
     *
     * @param date LocalDate representing the date to check
     * @return DayType representing the type of the day
     */
    public DayType getDayType(LocalDate date) {
        if (isHoliday(date)) {
            return DayType.HOLIDAY;
        }
        if (isWeekend(date)) {
            return DayType.WEEKEND;
        }
        return DayType.WEEKDAY;
    }

    /**
     * Helper method to determine if a day is chargeable based on tool type and day type.
     *
     * @param toolTypeDto ToolTypeDto containing tool type information
     * @param dayType DayType representing the type of the day
     * @return boolean indicating if the day is chargeable
     */
    private boolean isChargeableDay(ToolTypeDto toolTypeDto, DayType dayType) {
        switch (dayType) {
            case HOLIDAY:
                return toolTypeDto.isHolidayCharge();
            case WEEKEND:
                return toolTypeDto.isWeekendCharge();
            case WEEKDAY:
                return toolTypeDto.isWeekdayCharge();
            default:
                return false;
        }
    }

    /**
     * Helper method to determine if a date is a holiday.
     *
     * @param date LocalDate representing the date to check
     * @return boolean indicating if the date is a holiday
     */
    private boolean isHoliday(LocalDate date) {
        return holidayService.isHoliday(date);
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
