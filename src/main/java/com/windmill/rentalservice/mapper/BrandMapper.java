package com.windmill.rentalservice.mapper;

import com.windmill.rentalservice.dto.BrandDto;
import com.windmill.rentalservice.dto.CreateBrandDto;
import com.windmill.rentalservice.model.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    public BrandDto toDto(Brand brand) {
        if (brand == null) {
            return null;
        }

        return new BrandDto(
                brand.getBrandId(),
                brand.getBrandName()
        );
    }

    public Brand toEntity(BrandDto dto) {
        if (dto == null) {
            return null;
        }
        Brand brand = new Brand();
        brand.setBrandId(dto.getBrandId());
        brand.setBrandName(dto.getBrandName());
        return brand;
    }

    public Brand toEntity(CreateBrandDto dto) {
        if (dto == null) {
            return null;
        }
        Brand brand = new Brand();
        //brand.setBrandId(0L);
        brand.setBrandName(dto.getBrandName());
        return brand;
    }
}
