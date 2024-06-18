package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.HolidayDto;
import com.windmill.rentalservice.dto.CreateHolidayDto;
import com.windmill.rentalservice.mapper.HolidayMapper;
import com.windmill.rentalservice.model.Holiday;
import com.windmill.rentalservice.repository.HolidayRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;
    private final List<Holiday> cachedHolidays = new ArrayList<>();

    /**
     * Constructs a new HolidayService with the specified HolidayRepository and HolidayMapper.
     *
     * @param holidayRepository the repository for accessing holiday data
     * @param holidayMapper     the mapper for converting between Holiday entities and DTOs
     */
    @Autowired
    public HolidayService(HolidayRepository holidayRepository, HolidayMapper holidayMapper) {
        this.holidayRepository = holidayRepository;
        this.holidayMapper = holidayMapper;
        refreshCache();
    }

    /**
     * Creates a new holiday.
     *
     * @param createHolidayDto the DTO containing the details of the holiday to create
     * @return the created HolidayDto
     */
    public HolidayDto createHoliday(CreateHolidayDto createHolidayDto) {
        Holiday holiday = holidayMapper.toEntity(createHolidayDto);
        Holiday savedHoliday = holidayRepository.save(holiday);
        refreshCache();
        return holidayMapper.toDto(savedHoliday);
    }

    /**
     * Updates an existing holiday.
     *
     * @param id          the ID of the holiday to update
     * @param holidayDto  the DTO containing the updated details of the holiday
     * @return the updated HolidayDto
     */
    public HolidayDto updateHoliday(Long id, HolidayDto holidayDto) {
        Optional<Holiday> existingHolidayOpt = holidayRepository.findById(id);
        if (existingHolidayOpt.isPresent()) {
            Holiday existingHoliday = existingHolidayOpt.get();
            existingHoliday.setName(holidayDto.getName());
            existingHoliday.setYear(holidayDto.getYear());
            existingHoliday.setMonth(holidayDto.getMonth());
            existingHoliday.setDayOfMonth(holidayDto.getDayOfMonth());
            existingHoliday.setDayOfWeek(holidayDto.getDayOfWeek());
            existingHoliday.setWeekOfMonth(holidayDto.getWeekOfMonth());
            existingHoliday.setDurationDays(holidayDto.getDurationDays());
            existingHoliday.setHolidayType(holidayDto.getHolidayType());
            Holiday updatedHoliday = holidayRepository.save(existingHoliday);
            refreshCache();
            return holidayMapper.toDto(updatedHoliday);
        } else {
            throw new RuntimeException(AppConstants.HOLIDAY_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Deletes a holiday.
     *
     * @param id the ID of the holiday to delete
     */
    public void deleteHoliday(Long id) {
        if (holidayRepository.existsById(id)) {
            holidayRepository.deleteById(id);
            refreshCache();
        } else {
            throw new RuntimeException(AppConstants.HOLIDAY_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Retrieves a holiday by its ID.
     *
     * @param id the ID of the holiday to retrieve
     * @return the retrieved HolidayDto
     */
    @Transactional(readOnly = true)
    public HolidayDto getHolidayById(Long id) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(AppConstants.HOLIDAY_NOT_FOUND_ERROR + id));
        return holidayMapper.toDto(holiday);
    }

    /**
     * Retrieves all holidays.
     *
     * @return a list of all HolidayDto
     */
    @Transactional(readOnly = true)
    public List<HolidayDto> getAllHolidays() {
        return cachedHolidays.stream()
                .map(holidayMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Checks if a given date is a holiday.
     *
     * @param date the date to check
     * @return true if the date is a holiday, false otherwise
     */
    public boolean isHoliday(LocalDate date) {
        return cachedHolidays.stream().anyMatch(holiday -> holiday.isHoliday(date));
    }

    /**
     * Refreshes the cache of holidays by retrieving all holidays from the repository.
     */
    private void refreshCache() {
        cachedHolidays.clear();
        cachedHolidays.addAll(holidayRepository.findAll());
    }
}
