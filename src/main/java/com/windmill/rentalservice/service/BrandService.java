package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.BrandDto;
import com.windmill.rentalservice.dto.CreateBrandDto;
import com.windmill.rentalservice.mapper.BrandMapper;
import com.windmill.rentalservice.model.Brand;
import com.windmill.rentalservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.windmill.rentalservice.util.AppConstants.BRAND_NOT_FOUND_ERROR;

/**
 * Service class for handling Brand related operations.
 */
@Service
@Transactional
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    /**
     * Constructor for dependency injection.
     *
     * @param brandRepository BrandRepository for brand data operations
     * @param brandMapper BrandMapper for mapping between Brand entity and DTO
     */
    @Autowired
    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    /**
     * Creates a new Brand.
     *
     * @param createBrandDto CreateBrandDto containing brand creation data
     * @return BrandDto of the newly created brand
     */
    public BrandDto createBrand(CreateBrandDto createBrandDto) {
        Brand brand = brandMapper.toEntity(createBrandDto);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toDto(savedBrand);
    }

    /**
     * Updates an existing Brand.
     *
     * @param id Long id of the brand to update
     * @param brandDto BrandDto containing updated brand data
     * @return BrandDto of the updated brand
     */
    public BrandDto updateBrand(Long id, BrandDto brandDto) {
        Optional<Brand> existingBrandOpt = brandRepository.findById(id);
        if (existingBrandOpt.isPresent()) {
            Brand existingBrand = existingBrandOpt.get();
            existingBrand.setBrandName(brandDto.getBrandName());
            Brand updatedBrand = brandRepository.save(existingBrand);
            return brandMapper.toDto(updatedBrand);
        } else {
            throw new RuntimeException(BRAND_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Deletes a Brand by id.
     *
     * @param id Long id of the brand to delete
     */
    public void deleteBrand(Long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else {
            throw new RuntimeException(BRAND_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Retrieves a Brand by id.
     *
     * @param id Long id of the brand to retrieve
     * @return BrandDto of the retrieved brand
     */
    @Transactional(readOnly = true)
    public BrandDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(BRAND_NOT_FOUND_ERROR + id));
        return brandMapper.toDto(brand);
    }

    /**
     * Retrieves all Brands.
     *
     * @return List of BrandDto containing all brands
     */
    @Transactional(readOnly = true)
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(brandMapper::toDto)
                .collect(Collectors.toList());
    }
}
