package com.windmill.rentalservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "holidays")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_id")
    private Long holidayId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "the_year")
    private Integer year;

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

    /**
     * Checks if the given date is a holiday.
     *
     * @param date the date to check
     * @return true if the date is a holiday, otherwise false
     */
    public boolean isHoliday(LocalDate date) {
        LocalDate startDate = getObservedDate();
        LocalDate endDate = startDate.plusDays(durationDays - 1);
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    /**
     * Calculates the observed date of the holiday.
     *
     * @return the observed date
     */
    public LocalDate getObservedDate() {
        LocalDate observedDate = null;

        switch (holidayType) {
            case ABSOLUTE:
                observedDate = LocalDate.of(year, month, dayOfMonth);
                break;
            case RELATIVE:
                observedDate = calculateRelativeHoliday(year, month, weekOfMonth, dayOfWeek);
                break;
            case ANNUAL:
                observedDate = LocalDate.of(LocalDate.now().getYear(), month, dayOfMonth);
                return adjustForWeekend(observedDate);
        }

        return observedDate;
    }

    /**
     * Adjusts the date if it falls on a weekend.
     *
     * @param date the date to adjust
     * @return the adjusted date
     */
    private LocalDate adjustForWeekend(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return date.minusDays(1);
        } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return date.plusDays(1);
        }
        return date;
    }

    /**
     * Calculates the date of a relative holiday.
     *
     * @param year        the year
     * @param month       the month
     * @param weekOfMonth the week of the month
     * @param dayOfWeek   the day of the week
     * @return the calculated date
     */
    private LocalDate calculateRelativeHoliday(int year, int month, int weekOfMonth, int dayOfWeek) {
        LocalDate date = LocalDate.of(year, month, 1);

        int weekCount = 0;
        while (date.getMonthValue() == month) {
            if (date.getDayOfWeek().getValue() == dayOfWeek) {
                weekCount++;
                if (weekCount == weekOfMonth) {
                    return date;
                }
            }
            date = date.plusDays(1);
        }

        throw new IllegalStateException("Invalid relative holiday definition");
    }
}
