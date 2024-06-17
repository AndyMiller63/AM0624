package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.CreateToolTypeDto;
import com.windmill.rentalservice.dto.ToolTypeDto;
import com.windmill.rentalservice.service.ToolTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tooltypes")
@Tag(name = "Tool Types", description = "API for managing tool types")
public class ToolTypeController {

    @Autowired
    private ToolTypeService toolTypeService;

    @GetMapping
    @Operation(summary = "Get all tool types", description = "Retrieve a list of all tool types")
    public List<ToolTypeDto> getAllToolTypes() {
        return toolTypeService.getAllToolTypes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get tool type by ID", description = "Retrieve a tool type by its ID")
    public ToolTypeDto getToolTypeById(@PathVariable Long id) {
        return toolTypeService.getToolTypeById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new tool type", description = "Create a new tool type")
    public ToolTypeDto createToolType(@RequestBody CreateToolTypeDto createToolTypeDto) {
        return toolTypeService.createToolType(createToolTypeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a tool type", description = "Update an existing tool type")
    public ToolTypeDto updateToolType(@PathVariable Long id, @RequestBody ToolTypeDto toolTypeDto) {
        return toolTypeService.updateToolType(id, toolTypeDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a tool type", description = "Delete a tool type by its ID")
    public void deleteToolType(@PathVariable Long id) {
        toolTypeService.deleteToolType(id);
    }
}
