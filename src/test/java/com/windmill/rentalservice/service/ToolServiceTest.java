package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.ToolDto;
import com.windmill.rentalservice.mapper.ToolMapper;
import com.windmill.rentalservice.model.Tool;
import com.windmill.rentalservice.repository.ToolRepository;
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

class ToolServiceTest {

    AutoCloseable closeable;
    @Mock
    private ToolRepository toolRepository;

    @Mock
    private ToolMapper toolMapper;

    @InjectMocks
    private ToolService toolService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetToolById() {
        Long toolId = 1L;
        Tool tool = new Tool();
        ToolDto toolDto = new ToolDto(toolId, "toolCode", 1L, 1L);

        when(toolRepository.findById(toolId)).thenReturn(Optional.of(tool));
        when(toolMapper.toDto(tool)).thenReturn(toolDto);

        ToolDto result = toolService.getToolById(toolId);

        assertNotNull(result);
        assertEquals(toolId, result.getToolId());
    }
}
