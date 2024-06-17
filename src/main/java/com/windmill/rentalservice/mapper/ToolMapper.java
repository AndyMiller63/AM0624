package com.windmill.rentalservice.mapper;

import com.windmill.rentalservice.dto.CreateToolDto;
import com.windmill.rentalservice.dto.ToolDto;
import com.windmill.rentalservice.model.Tool;
import org.springframework.stereotype.Component;

@Component
public class ToolMapper {
    public ToolDto toDto(Tool tool) {
        if (tool == null) {
            return null;
        }
        return new ToolDto(
                tool.getToolId(),
                tool.getToolCode(),
                tool.getToolTypeId(),
                tool.getBrandId()
        );
    }

    public Tool toEntity(ToolDto dto) {
        if (dto == null) {
            return null;
        }
        Tool tool = new Tool();
        tool.setToolId(dto.getToolId());
        tool.setToolCode(dto.getToolCode());
        tool.setToolTypeId(dto.getToolTypeId());
        tool.setBrandId(dto.getBrandId());
        return tool;
    }

    public Tool toEntity(CreateToolDto dto) {
        if (dto == null) {
            return null;
        }
        Tool tool = new Tool();
        tool.setToolId(0L);
        tool.setToolCode(dto.getToolCode());
        tool.setToolTypeId(dto.getToolTypeId());
        tool.setBrandId(dto.getBrandId());
        return tool;
    }
}
