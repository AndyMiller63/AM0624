package com.windmill.rentalservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tool_quantities")
public class ToolQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_quantity_id")
    private Long toolQuantityId;

    @Column(name = "tool_id", nullable = false)
    private Long toolId;

    @Column(name = "in_stock_count", nullable = false)
    private int inStockCount;
}
