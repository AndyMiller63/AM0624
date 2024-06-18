package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.ToolQuantityDto;
import com.windmill.rentalservice.mapper.ToolQuantityMapper;
import com.windmill.rentalservice.model.ToolQuantity;
import com.windmill.rentalservice.repository.ToolQuantityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ToolQuantityServiceTest {

    AutoCloseable closeable;
    @Mock
    private ToolQuantityRepository toolQuantityRepository;

    @Mock
    private ToolQuantityMapper toolQuantityMapper;

    @InjectMocks
    private ToolQuantityService toolQuantityService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void testGetToolQuantityByToolId() {
        Long toolId = 1L;
        ToolQuantity toolQuantity = new ToolQuantity();
        ToolQuantityDto toolQuantityDto = new ToolQuantityDto(1L, toolId, 10);

        when(toolQuantityRepository.findByToolId(toolId)).thenReturn(Optional.of(toolQuantity));
        when(toolQuantityMapper.toDto(toolQuantity)).thenReturn(toolQuantityDto);

        ToolQuantityDto result = toolQuantityService.getToolQuantityByToolId(toolId);

        assertNotNull(result);
        assertEquals(10, result.getInStockCount());
    }
}
