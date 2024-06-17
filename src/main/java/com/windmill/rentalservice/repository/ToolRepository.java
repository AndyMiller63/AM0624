package com.windmill.rentalservice.repository;

import com.windmill.rentalservice.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, Long> {
    @Query("SELECT t FROM Tool t WHERE t.toolCode = :toolCode")
    List<Tool> findByToolCode(@Param("toolCode") String toolCode);
    Optional<Tool> findToolByToolId(Long toolId);
}
