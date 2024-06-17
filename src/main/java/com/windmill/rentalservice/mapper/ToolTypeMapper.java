package com.windmill.rentalservice.mapper;

import com.windmill.rentalservice.dto.CreateToolTypeDto;
import com.windmill.rentalservice.dto.ToolTypeDto;
import com.windmill.rentalservice.model.ToolType;
import org.springframework.stereotype.Component;

@Component
public class ToolTypeMapper {
    public ToolTypeDto toDto(ToolType toolType) {
        if (toolType == null) {
            return null;
        }
        return new ToolTypeDto(
                toolType.getToolTypeId(),
                toolType.getToolTypeName(),
                toolType.getDailyCharge(),
                toolType.isWeekdayCharge(),
                toolType.isWeekendCharge(),
                toolType.isHolidayCharge()
        );
    }

    public ToolType toEntity(ToolTypeDto dto) {
        if (dto == null) {
            return null;
        }
        ToolType toolType = new ToolType();
        toolType.setToolTypeId((dto.getToolTypeId()));
        toolType.setToolTypeName(dto.getToolTypeName());
        toolType.setDailyCharge(dto.getDailyCharge());
        toolType.setWeekdayCharge(dto.isWeekdayCharge());
        toolType.setWeekendCharge(dto.isWeekendCharge());
        toolType.setHolidayCharge(dto.isHolidayCharge());
        return toolType;
    }

    public ToolType toEntity(CreateToolTypeDto dto) {
        if (dto == null) {
            return null;
        }
        ToolType toolType = new ToolType();
        toolType.setToolTypeId(0L);
        toolType.setToolTypeName(dto.getToolTypeName());
        toolType.setDailyCharge(dto.getDailyCharge());
        toolType.setWeekdayCharge(dto.isWeekdayCharge());
        toolType.setWeekendCharge(dto.isWeekendCharge());
        toolType.setHolidayCharge(dto.isHolidayCharge());
        return toolType;
    }
}
