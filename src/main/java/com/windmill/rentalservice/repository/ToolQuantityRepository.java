package com.windmill.rentalservice.repository;

import com.windmill.rentalservice.model.ToolQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToolQuantityRepository extends JpaRepository<ToolQuantity, Long> {
    Optional<ToolQuantity> findByToolId(Long toolId);
}
