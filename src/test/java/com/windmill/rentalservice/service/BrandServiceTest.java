package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.BrandDto;
import com.windmill.rentalservice.dto.CreateBrandDto;
import com.windmill.rentalservice.mapper.BrandMapper;
import com.windmill.rentalservice.model.Brand;
import com.windmill.rentalservice.repository.BrandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class BrandServiceTest {
    private AutoCloseable closeable;
    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @InjectMocks
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void testCreateBrand() {
        CreateBrandDto createBrandDto = new CreateBrandDto("brandName");
        Brand brand = new Brand();
        brand.setBrandName("brandName");
        BrandDto brandDto = new BrandDto(1L, "brandName");

        when(brandMapper.toEntity(createBrandDto)).thenReturn(brand);
        when(brandRepository.save(brand)).thenReturn(brand);
        when(brandMapper.toDto(brand)).thenReturn(brandDto);

        BrandDto result = brandService.createBrand(createBrandDto);

        assertNotNull(result);
        assertEquals("brandName", result.getBrandName());
    }

    @Test
    void testUpdateBrand() {
        Long id = 1L;
        BrandDto brandDto = new BrandDto(id, "updatedBrandName");
        Brand brand = new Brand();
        brand.setBrandName("brandName");

        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        when(brandMapper.toDto(brand)).thenReturn(brandDto);

        BrandDto result = brandService.updateBrand(id, brandDto);

        assertNotNull(result);
        assertEquals("updatedBrandName", result.getBrandName());
    }

    @Test
    void testDeleteBrand() {
        Long id = 1L;

        when(brandRepository.existsById(id)).thenReturn(true);

        brandService.deleteBrand(id);

        verify(brandRepository, times(1)).deleteById(id);
    }
}
