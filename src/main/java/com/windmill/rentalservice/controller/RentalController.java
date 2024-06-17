package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.CreateRentalDto;
import com.windmill.rentalservice.dto.RentalDto;
import com.windmill.rentalservice.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new rental",
            description = "Create a new rental record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the rental to be created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateRentalDto.class),
                            examples = @ExampleObject(value = "{\"toolId\": 1, \"customerId\": 1, \"rentalDays\": 5, \"discountPercent\": 10, \"checkoutDateString\": \"06/10/24\"}")
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created the rental",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RentalDto.class),
                                    examples = @ExampleObject(value = "{\"rentalId\": 1, \"toolId\": 1, \"customerId\": 1, \"rentalDays\": 5, \"discountPercent\": 10, \"checkoutDate\": \"06/20/24\", \"dueDate\": \"06/25/24\", \"chargeDays\": 4, \"preDiscountCharge\": 7.96, \"discountAmount\": 0.80, \"finalCharge\": 7.16}")
                            )
                    )
            }
    )
    public ResponseEntity<RentalDto> createRental(@RequestBody CreateRentalDto createRentalDto) throws Exception {
        RentalDto rentalDto = rentalService.createRental(createRentalDto);
        return new ResponseEntity<>(rentalDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get rental by ID",
            description = "Retrieve a rental by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the rental",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RentalDto.class),
                                    examples = @ExampleObject(value = "{\"rentalId\": 1, \"toolId\": 1, \"customerId\": 1, \"rentalDays\": 5, \"discountPercent\": 10, \"checkoutDate\": \"06/20/24\", \"dueDate\": \"06/25/24\", \"chargeDays\": 4, \"preDiscountCharge\": 7.96, \"discountAmount\": 0.80, \"finalCharge\": 7.16}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Rental not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"error\": \"Rental not found\"}")
                            )
                    )
            }
    )
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id) {
        RentalDto rentalDto = rentalService.getRentalById(id);
        return new ResponseEntity<>(rentalDto, HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Get all rentals",
            description = "Retrieve all rentals",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved list of rentals",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RentalDto.class),
                                    examples = @ExampleObject(value = "[{\"rentalId\": 1, \"toolId\": 1, \"customerId\": 1, \"rentalDays\": 5, \"discountPercent\": 10, \"checkoutDate\": \"06/20/24\", \"dueDate\": \"06/25/24\", \"chargeDays\": 4, \"preDiscountCharge\": 7.96, \"discountAmount\": 0.80, \"finalCharge\": 7.16}]")
                            )
                    )
            }
    )
    public ResponseEntity<List<RentalDto>> getAllRentals() {
        List<RentalDto> rentals = rentalService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update rental by ID",
            description = "Update the details of a rental by its ID",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated rental details",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RentalDto.class),
                            examples = @ExampleObject(value = "{\"rentalId\": 1, \"toolId\": 1, \"customerId\": 1, \"rentalDays\": 5, \"discountPercent\": 10, \"checkoutDate\": \"06/20/24\", \"dueDate\": \"06/25/24\", \"chargeDays\": 4, \"preDiscountCharge\": 7.96, \"discountAmount\": 0.80, \"finalCharge\": 7.16}")
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated the rental",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RentalDto.class),
                                    examples = @ExampleObject(value = "{\"rentalId\": 1, \"toolId\": 1, \"customerId\": 1, \"rentalDays\": 5, \"discountPercent\": 10, \"checkoutDate\": \"06/20/24\", \"dueDate\": \"06/25/24\", \"chargeDays\": 4, \"preDiscountCharge\": 7.96, \"discountAmount\": 0.80, \"finalCharge\": 7.16}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Rental not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"error\": \"Rental not found\"}")
                            )
                    )
            }
    )
    public ResponseEntity<RentalDto> updateRental(@PathVariable Long id, @RequestBody RentalDto rentalDto) {
        RentalDto updatedRental = rentalService.updateRental(id, rentalDto);
        return new ResponseEntity<>(updatedRental, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete rental by ID",
            description = "Delete a rental by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted the rental"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Rental not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"error\": \"Rental not found\"}")
                            )
                    )
            }
    )
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
