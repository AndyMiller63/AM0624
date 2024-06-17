package com.windmill.rentalservice.mapper;

import com.windmill.rentalservice.dto.ToolQuantityDto;
import com.windmill.rentalservice.model.ToolQuantity;
import org.springframework.stereotype.Component;

@Component
public class ToolQuantityMapper {

    public ToolQuantityDto toDto(ToolQuantity toolQuantity) {
        if (toolQuantity == null) {
            return null;
        }

        return new ToolQuantityDto(
                toolQuantity.getToolQuantityId(),
                toolQuantity.getToolId(),
                toolQuantity.getInStockCount()
        );
    }

    public ToolQuantity toEntity(ToolQuantityDto dto) {
        if (dto == null) {
            return null;
        }

        ToolQuantity toolQuantity = new ToolQuantity();
        toolQuantity.setToolQuantityId(dto.getToolQuantityId());
        toolQuantity.setToolId(dto.getToolId());
        toolQuantity.setInStockCount(dto.getInStockCount());
        return toolQuantity;
    }
}
