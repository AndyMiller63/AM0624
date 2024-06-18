package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.ToolTypeDto;
import com.windmill.rentalservice.mapper.ToolTypeMapper;
import com.windmill.rentalservice.model.ToolType;
import com.windmill.rentalservice.repository.ToolTypeRepository;
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

class ToolTypeServiceTest {

    AutoCloseable closeable;
    @Mock
    private ToolTypeRepository toolTypeRepository;

    @Mock
    private ToolTypeMapper toolTypeMapper;

    @InjectMocks
    private ToolTypeService toolTypeService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void testGetToolTypeById() {
        Long toolTypeId = 1L;
        ToolType toolType = new ToolType();
        ToolTypeDto toolTypeDto = new ToolTypeDto(toolTypeId, "toolTypeName", 10.0, true, true, true);

        when(toolTypeRepository.findById(toolTypeId)).thenReturn(Optional.of(toolType));
        when(toolTypeMapper.toDto(toolType)).thenReturn(toolTypeDto);

        ToolTypeDto result = toolTypeService.getToolTypeById(toolTypeId);

        assertNotNull(result);
        assertEquals(toolTypeId, result.getToolTypeId());
    }
}
