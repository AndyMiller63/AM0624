package com.windmill.rentalservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "holidays")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holidayId;

    @Column(name = "name", nullable = false)
    private String name;

    // db has year as a reserved word...
    @Column(name = "the_year")
    private Integer year;

    // try and keep it a little consistent
    @Column(name = "the_month")
    private Integer month;

    @Column(name = "day_of_month")
    private Integer dayOfMonth;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column(name = "week_of_month")
    private Integer weekOfMonth;

    @Column(name = "duration_days", nullable = false)
    private int durationDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "holiday_type", nullable = false)
    private HolidayType holidayType;

    public enum HolidayType {
        ABSOLUTE, RELATIVE, ANNUAL
    }

    public LocalDate getObservedDate(int year) {
        switch (holidayType) {
            case ABSOLUTE:
                return LocalDate.of(this.year != null ? this.year : year, month, dayOfMonth);
            case RELATIVE:
                LocalDate date = LocalDate.of(year, month, 1)
                        .with(DayOfWeek.of(dayOfWeek))
                        .plusWeeks(weekOfMonth - 1);
                return adjustForWeekend(date);
            case ANNUAL:
                return adjustForWeekend(LocalDate.of(year, month, dayOfMonth));
            default:
                throw new IllegalStateException("Unexpected value: " + holidayType);
        }
    }

    private LocalDate adjustForWeekend(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return date.minusDays(1);
        } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return date.plusDays(1);
        }
        return date;
    }
}
