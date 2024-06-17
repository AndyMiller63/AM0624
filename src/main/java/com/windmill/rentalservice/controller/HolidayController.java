package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.CreateHolidayDto;
import com.windmill.rentalservice.dto.HolidayDto;
import com.windmill.rentalservice.service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/holidays")
@Tag(name = "Holidays", description = "API for managing holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping
    @Operation(summary = "Get all holidays", description = "Retrieve a list of all holidays")
    public List<HolidayDto> getAllHolidays() {
        return holidayService.getAllHolidays();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get holiday by ID", description = "Retrieve a holiday by its ID")
    public HolidayDto getHolidayById(@PathVariable Long id) {
        return holidayService.getHolidayById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new holiday", description = "Create a new holiday")
    public HolidayDto createHoliday(@RequestBody CreateHolidayDto createHolidayDto) {
        return holidayService.createHoliday(createHolidayDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a holiday", description = "Update an existing holiday")
    public HolidayDto updateHoliday(@PathVariable Long id, @RequestBody HolidayDto holidayDto) {
        return holidayService.updateHoliday(id, holidayDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a holiday", description = "Delete a holiday by its ID")
    public void deleteHoliday(@PathVariable Long id) {
        holidayService.deleteHoliday(id);
    }
}
