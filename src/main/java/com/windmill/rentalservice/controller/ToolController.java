package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.CreateToolDto;
import com.windmill.rentalservice.dto.ToolDto;
import com.windmill.rentalservice.service.ToolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tools")
@Tag(name = "Tool Management System", description = "Operations related to tools")
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @PostMapping
    @Operation(summary = "Create a new tool", description = "Create a new tool with the provided details")
    public ToolDto createTool(@RequestBody CreateToolDto createToolDto) {
        return toolService.createTool(createToolDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a tool by ID", description = "Retrieve a tool by its ID")
    public ToolDto getToolById(@PathVariable Long id) {
        return toolService.getToolById(id);
    }

    @GetMapping
    @Operation(summary = "Get all tools", description = "Retrieve a list of all tools")
    public List<ToolDto> getAllTools() {
        return toolService.getAllTools();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a tool", description = "Update the details of an existing tool")
    public ToolDto updateTool(@PathVariable Long id, @RequestBody ToolDto toolDto) {
        return toolService.updateTool(id, toolDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a tool by ID", description = "Delete a tool by its ID")
    public void deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
    }
}
