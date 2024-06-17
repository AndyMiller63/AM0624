package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.CreateHolidayDto;
import com.windmill.rentalservice.dto.HolidayDto;
import com.windmill.rentalservice.mapper.HolidayMapper;
import com.windmill.rentalservice.model.Holiday;
import com.windmill.rentalservice.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.windmill.rentalservice.util.AppConstants.HOLIDAY_NOT_FOUND_ERROR;

/**
 * Service class for handling Holiday related operations.
 */
@Service
@Transactional
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;

    /**
     * Constructor for dependency injection.
     *
     * @param holidayRepository HolidayRepository for holiday data operations
     * @param holidayMapper HolidayMapper for mapping between Holiday entity and DTO
     */
    @Autowired
    public HolidayService(HolidayRepository holidayRepository, HolidayMapper holidayMapper) {
        this.holidayRepository = holidayRepository;
        this.holidayMapper = holidayMapper;
    }

    /**
     * Creates a new Holiday.
     *
     * @param createHolidayDto CreateHolidayDto containing holiday creation data
     * @return HolidayDto of the newly created holiday
     */
    public HolidayDto createHoliday(CreateHolidayDto createHolidayDto) {
        Holiday holiday = holidayMapper.toEntity(createHolidayDto);
        Holiday savedHoliday = holidayRepository.save(holiday);
        return holidayMapper.toDto(savedHoliday);
    }

    /**
     * Updates an existing Holiday.
     *
     * @param id Long id of the holiday to update
     * @param holidayDto HolidayDto containing updated holiday data
     * @return HolidayDto of the updated holiday
     */
    public HolidayDto updateHoliday(Long id, HolidayDto holidayDto) {
        Optional<Holiday> existingHolidayOpt = holidayRepository.findById(id);
        if (existingHolidayOpt.isPresent()) {
            Holiday existingHoliday = existingHolidayOpt.get();
            Holiday updatedHoliday = holidayMapper.toEntity(holidayDto);
            updatedHoliday = holidayRepository.save(existingHoliday);
            return holidayMapper.toDto(updatedHoliday);
        } else {
            throw new RuntimeException(HOLIDAY_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Deletes a Holiday by id.
     *
     * @param id Long id of the holiday to delete
     */
    public void deleteHoliday(Long id) {
        if (holidayRepository.existsById(id)) {
            holidayRepository.deleteById(id);
        } else {
            throw new RuntimeException(HOLIDAY_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Retrieves a Holiday by id.
     *
     * @param id Long id of the holiday to retrieve
     * @return HolidayDto of the retrieved holiday
     */
    @Transactional(readOnly = true)
    public HolidayDto getHolidayById(Long id) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(HOLIDAY_NOT_FOUND_ERROR + id));
        return holidayMapper.toDto(holiday);
    }

    /**
     * Retrieves all Holidays.
     *
     * @return List of HolidayDto containing all holidays
     */
    @Transactional(readOnly = true)
    public List<HolidayDto> getAllHolidays() {
        return holidayRepository.findAll().stream()
                .map(holidayMapper::toDto)
                .collect(Collectors.toList());
    }
}
