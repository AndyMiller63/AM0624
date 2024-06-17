package com.windmill.rentalservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tools")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_id")
    private Long toolId;

    @Column(name = "tool_code", nullable = false)
    private String toolCode;

    @Column(name = "tool_type_id", nullable = false)
    private Long toolTypeId;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

}
