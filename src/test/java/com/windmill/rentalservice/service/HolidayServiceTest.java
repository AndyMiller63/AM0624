package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.CreateHolidayDto;
import com.windmill.rentalservice.dto.HolidayDto;
import com.windmill.rentalservice.mapper.HolidayMapper;
import com.windmill.rentalservice.model.Holiday;
import com.windmill.rentalservice.repository.HolidayRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class HolidayServiceTest {

    AutoCloseable closeable;
    @Mock
    private HolidayRepository holidayRepository;

    @Mock
    private HolidayMapper holidayMapper;

    @InjectMocks
    private HolidayService holidayService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void testCreateHoliday() {
        CreateHolidayDto createHolidayDto = new CreateHolidayDto("holidayName", 2023, 12, 25, 1, 1, 1, Holiday.HolidayType.ANNUAL);
        Holiday holiday = new Holiday();
        holiday.setName("holidayName");
        HolidayDto holidayDto = new HolidayDto(1L, "holidayName", 2023, 12, 25, 1, 1, 1, Holiday.HolidayType.ANNUAL);

        when(holidayMapper.toEntity(createHolidayDto)).thenReturn(holiday);
        when(holidayRepository.save(holiday)).thenReturn(holiday);
        when(holidayMapper.toDto(holiday)).thenReturn(holidayDto);

        HolidayDto result = holidayService.createHoliday(createHolidayDto);

        assertNotNull(result);
        assertEquals("holidayName", result.getName());
    }

    @Test
    void testUpdateHoliday() {
        Long id = 1L;
        HolidayDto holidayDto = new HolidayDto(id, "Chrismas", 2024, 12, 25,
                1, 1, 1, Holiday.HolidayType.ANNUAL);
        Holiday holiday = new Holiday();
        holiday.setName("Christmas");

        when(holidayRepository.findById(id)).thenReturn(Optional.of(holiday));
        when(holidayRepository.save(any(Holiday.class))).thenReturn(holiday);
        when(holidayMapper.toDto(holiday)).thenReturn(holidayDto);

        HolidayDto result = holidayService.updateHoliday(id, holidayDto);

        assertNotNull(result);
        assertEquals("Christmas", result.getName());
    }

    @Test
    void testDeleteHoliday() {
        Long id = 1L;

        when(holidayRepository.existsById(id)).thenReturn(true);

        holidayService.deleteHoliday(id);

        verify(holidayRepository, times(1)).deleteById(id);
    }
}
