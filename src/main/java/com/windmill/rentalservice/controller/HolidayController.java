package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.CreateHolidayDto;
import com.windmill.rentalservice.dto.HolidayDto;
import com.windmill.rentalservice.service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/holidays")
public class HolidayController {

    private final HolidayService holidayService;

    /**
     * Constructs a new HolidayController with the specified HolidayService.
     *
     * @param holidayService the service for handling holiday-related operations
     */
    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    /**
     * Creates a new holiday.
     *
     * @param createHolidayDto the DTO containing the details of the holiday to create
     * @return the created HolidayDto
     */
    @PostMapping
    @Operation(summary = "Create a new holiday", description = "Create a new holiday with the given details")
    public ResponseEntity<HolidayDto> createHoliday(@RequestBody CreateHolidayDto createHolidayDto) {
        HolidayDto holidayDto = holidayService.createHoliday(createHolidayDto);
        return ResponseEntity.ok(holidayDto);
    }

    /**
     * Updates an existing holiday.
     *
     * @param id          the ID of the holiday to update
     * @param holidayDto  the DTO containing the updated details of the holiday
     * @return the updated HolidayDto
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing holiday", description = "Update an existing holiday with the given details")
    public ResponseEntity<HolidayDto> updateHoliday(@PathVariable Long id, @RequestBody HolidayDto holidayDto) {
        HolidayDto updatedHoliday = holidayService.updateHoliday(id, holidayDto);
        return ResponseEntity.ok(updatedHoliday);
    }

    /**
     * Deletes a holiday.
     *
     * @param id the ID of the holiday to delete
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a holiday", description = "Delete a holiday by its ID")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        holidayService.deleteHoliday(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a holiday by its ID.
     *
     * @param id the ID of the holiday to retrieve
     * @return the retrieved HolidayDto
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a holiday by ID", description = "Retrieve a holiday by its ID")
    public ResponseEntity<HolidayDto> getHolidayById(@PathVariable Long id) {
        HolidayDto holidayDto = holidayService.getHolidayById(id);
        return ResponseEntity.ok(holidayDto);
    }

    /**
     * Retrieves all holidays.
     *
     * @return a list of all HolidayDto
     */
    @GetMapping
    @Operation(summary = "Get all holidays", description = "Retrieve a list of all holidays")
    public ResponseEntity<List<HolidayDto>> getAllHolidays() {
        List<HolidayDto> holidays = holidayService.getAllHolidays();
        return ResponseEntity.ok(holidays);
    }
}
