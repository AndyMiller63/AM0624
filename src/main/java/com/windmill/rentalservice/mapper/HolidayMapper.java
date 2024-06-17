package com.windmill.rentalservice.mapper;

import com.windmill.rentalservice.dto.CreateHolidayDto;
import com.windmill.rentalservice.dto.HolidayDto;
import com.windmill.rentalservice.model.Holiday;
import org.springframework.stereotype.Component;

@Component
public class HolidayMapper {

    public HolidayDto toDto(Holiday holiday) {
        if (holiday == null) {
            return null;
        }

        return new HolidayDto(
                holiday.getHolidayId(),
                holiday.getName(),
                holiday.getYear(),
                holiday.getMonth(),
                holiday.getDayOfMonth(),
                holiday.getDayOfWeek(),
                holiday.getWeekOfMonth(),
                holiday.getDurationDays(),
                holiday.getHolidayType()
        );
    }

    public Holiday toEntity(HolidayDto dto) {
        if (dto == null) {
            return null;
        }
        Holiday holiday = new Holiday();
        holiday.setHolidayId(dto.getHolidayId());
        holiday.setName(dto.getName());
        holiday.setYear(dto.getYear());
        holiday.setMonth(dto.getMonth());
        holiday.setDayOfMonth(dto.getDayOfMonth());
        holiday.setDayOfWeek(dto.getDayOfWeek());
        holiday.setWeekOfMonth(dto.getWeekOfMonth());
        holiday.setDurationDays(dto.getDurationDays());
        holiday.setHolidayType(dto.getHolidayType());
        return holiday;
    }

    public Holiday toEntity(CreateHolidayDto dto) {
        if (dto == null) {
            return null;
        }
        Holiday holiday = new Holiday();
        holiday.setHolidayId(0L);
        holiday.setName(dto.getName());
        holiday.setYear(dto.getYear());
        holiday.setMonth(dto.getMonth());
        holiday.setDayOfMonth(dto.getDayOfMonth());
        holiday.setDayOfWeek(dto.getDayOfWeek());
        holiday.setWeekOfMonth(dto.getWeekOfMonth());
        holiday.setDurationDays(dto.getDurationDays());
        holiday.setHolidayType(dto.getHolidayType());
        return holiday;
    }
}
