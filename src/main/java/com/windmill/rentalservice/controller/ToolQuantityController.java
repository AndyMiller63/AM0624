package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.ToolQuantityDto;
import com.windmill.rentalservice.service.ToolQuantityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tool-quantities")
@Tag(name = "Tool Quantity Management System", description = "Operations related to tool quantities")
public class ToolQuantityController {

    private final ToolQuantityService toolQuantityService;

    @Autowired
    public ToolQuantityController(ToolQuantityService toolQuantityService) {
        this.toolQuantityService = toolQuantityService;
    }

    @GetMapping("/{toolId}")
    @Operation(summary = "Get tool quantity by tool ID", description = "Retrieve the quantity of a tool by its ID")
    public ToolQuantityDto getToolQuantityByToolId(@PathVariable Long toolId) {
        return toolQuantityService.getToolQuantityByToolId(toolId);
    }

    @GetMapping
    @Operation(summary = "Get all tool quantities", description = "Retrieve a list of all tool quantities")
    public List<ToolQuantityDto> getAllToolQuantities() {
        return toolQuantityService.getAllToolQuantities();
    }
}
