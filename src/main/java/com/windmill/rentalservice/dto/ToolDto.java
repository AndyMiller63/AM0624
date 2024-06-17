package com.windmill.rentalservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ToolDto implements Serializable {
    private Long toolId;
    private String toolCode;
    private Long toolTypeId;
    private Long brandId;

    public ToolDto() {

    }
    public ToolDto(Long toolId, String toolCode, Long toolTypeId, Long brandId) {
        this.toolId = toolId;
        this.toolCode = toolCode;
        this.toolTypeId = toolTypeId;
        this.brandId = brandId;

    }
}
