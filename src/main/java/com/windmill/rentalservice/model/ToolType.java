package com.windmill.rentalservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tool_types")
public class ToolType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_type_id")
    private Long toolTypeId;

    @Column(name = "tool_type_name", nullable = false)
    private String toolTypeName;

    @Column(name = "daily_charge", nullable = false)
    private double dailyCharge;

    @Column(name = "weekday_charge", nullable = false)
    private boolean weekdayCharge;

    @Column(name = "weekend_charge", nullable = false)
    private boolean weekendCharge;

    @Column(name = "holiday_charge", nullable = false)
    private boolean holidayCharge;

}
