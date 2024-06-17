package com.windmill.rentalservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    @Column(name = "tool_id", nullable = false)
    private Long toolId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "rental_days", nullable = false)
    private int rentalDays;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent;

    @Column(name = "checkout_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate checkoutDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "charge_days", nullable = false)
    private int chargeDays;

    @Column(name = "pre_discount_charge", nullable = false)
    private BigDecimal preDiscountCharge;

    @Column(name = "discount_amount", nullable = false)
    private BigDecimal discountAmount;

    @Column(name = "final_charge", nullable = false)
    private BigDecimal finalCharge;

    public Rental() {
    }

    public Rental(Long toolId, Long customerId, int rentalDays, LocalDate checkoutDate, LocalDate dueDate,
                  int chargeDays, BigDecimal preDiscountCharge,
                  int discountPercent, BigDecimal discountAmount, BigDecimal finalCharge) {
        this.toolId = toolId;
        this.customerId = customerId;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

}