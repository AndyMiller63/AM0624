package com.windmill.rentalservice.repository;

import com.windmill.rentalservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findBrandByBrandId(Long brandId);
}
