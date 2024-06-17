package com.windmill.rentalservice.controller;

import com.windmill.rentalservice.dto.BrandDto;
import com.windmill.rentalservice.dto.CreateBrandDto;
import com.windmill.rentalservice.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@Tag(name = "Brands", description = "API for managing brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    @Operation(summary = "Get all brands", description = "Retrieve a list of all brands")
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get brand by ID", description = "Retrieve a brand by its ID")
    public BrandDto getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new brand", description = "Create a new brand")
    public BrandDto createBrand(@RequestBody CreateBrandDto createBrandDto) {
        return brandService.createBrand(createBrandDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a brand", description = "Update an existing brand")
    public BrandDto updateBrand(@PathVariable Long id, @RequestBody BrandDto brandDto) {
        return brandService.updateBrand(id, brandDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a brand", description = "Delete a brand by its ID")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}
