package com.windmill.rentalservice.repository;

import com.windmill.rentalservice.model.ToolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ToolTypeRepository extends JpaRepository<ToolType, Long> {
    @Query("SELECT t FROM ToolType t WHERE t.toolTypeName = :toolTypeName")
    List<ToolType> findByToolTypeName(@Param("toolTypeName") String toolTypeName);

    Optional<ToolType> findToolTypeByToolTypeId(@Param("toolTypeId") Long toolTypeId);
}

