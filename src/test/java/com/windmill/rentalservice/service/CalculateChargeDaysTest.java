package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.ToolTypeDto;
import com.windmill.rentalservice.model.Holiday;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CalculateChargeDaysTest {

    private AutoCloseable closeable;

    @Mock
    private HolidayService holidayService;

    @InjectMocks
    private CalculateChargeDays calculateChargeDays;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void testGetChargeDaysCount() {
        ToolTypeDto toolTypeDto = new ToolTypeDto(1L, "toolTypeName", 10.0, true, true, true);
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        int numberOfDays = 7;

        when(holidayService.getAllHolidays()).thenReturn(Collections.emptyList());

        int chargeDaysCount = calculateChargeDays.getChargeDaysCount(toolTypeDto, startDate, numberOfDays);

        assertEquals(7, chargeDaysCount);
    }

    @Test
    void testGetDayType() {

        Holiday christmas = new Holiday();
        christmas.setName("Christmas");
        //christmas.setYear(2023);
        christmas.setMonth(12);
        christmas.setDayOfMonth(25);
        christmas.setHolidayType(Holiday.HolidayType.ANNUAL);

        LocalDate holiday = LocalDate.of(2023, 12, 25);
        LocalDate weekend = LocalDate.of(2023, 1, 1);
        LocalDate weekday = LocalDate.of(2023, 1, 2);

        assertEquals(CalculateChargeDays.DayType.HOLIDAY, calculateChargeDays.getDayType(holiday));
        assertEquals(CalculateChargeDays.DayType.WEEKEND, calculateChargeDays.getDayType(weekend));
        assertEquals(CalculateChargeDays.DayType.WEEKDAY, calculateChargeDays.getDayType(weekday));
    }

}
